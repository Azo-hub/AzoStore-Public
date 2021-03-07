package AzoStore.oauth2;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import AzoStore.ModelPackage.AuthenticationProvider;
import AzoStore.ModelPackage.Role;
import AzoStore.ModelPackage.User;
import AzoStore.ModelPackage.UserRole;
import AzoStore.ServicePackage.UserService;


@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		
		String email = oAuth2User.getEmail();
		
		User user = userService.findByEmail(email);
		
		String name = oAuth2User.getName();
		
		if (user == null) {
			//register as new customer
			Role role = new Role();
			role.setRoleId(1);
			role.setName("ROLE_USER");
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole (user, role));
			
			
			userService.createNewUserAfterOAuthLoginSuccess(userRoles,email,name,AuthenticationProvider.GOOGLE);
		}
		
		else {
			//update existing customer
			
			userService.updateUserAfterOAuthLoginSuccess(user,name,AuthenticationProvider.GOOGLE);
			
		}
		System.out.println("customer's email: " + email);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
