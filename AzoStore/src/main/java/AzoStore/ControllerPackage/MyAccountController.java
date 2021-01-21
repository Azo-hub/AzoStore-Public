package AzoStore.ControllerPackage;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AzoStore.ModelPackage.MailConstructor;
import AzoStore.ModelPackage.PasswordResetToken;
import AzoStore.ModelPackage.Role;
import AzoStore.ModelPackage.SecurityUtility;
import AzoStore.ModelPackage.UserRole;
import AzoStore.ServicePackage.UserSecurityService;
import AzoStore.ServicePackage.UserService;

@Controller
public class MyAccountController {
	
	//private User user;
	
	
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	private MailConstructor mailConstructor;
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping("/newUser")
	public String newUser(Locale locale, @RequestParam ("token") String token,  Model model) {
		PasswordResetToken passToken = userService.getPasswordResetToken(token);
		
		if (passToken == null) {
			String message = "Invalid Token.";
			model.addAttribute("message", message);
			return "redirect:/badRequest";
		}
		
		AzoStore.ModelPackage.User user = passToken.getUser();
		String username = user.getUsername();
		UserDetails userDetails = userSecurityService.loadUserByUsername(username);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken (userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		model.addAttribute("user", user);
		
		model.addAttribute("classActiveEdit", true);
		return "myprofile";
	}
	
	
	@PostMapping("/newUser")
	public String newUserPost (HttpServletRequest request, @ModelAttribute("email") String userEmail, @ModelAttribute("username") String username, Model model) throws Exception {
		model.addAttribute("classActiveNewAccount", true);
		model.addAttribute("email", userEmail);
		model.addAttribute("username", username);
		
		
		if (userService.findByUsername(username) != null) {
			model.addAttribute("usernameExists", true);
			
			
			return "myaccount";
		}
		
		
		if (userService.findByEmail(userEmail) != null) {
			
			model.addAttribute("emailExists", true);
			
			return "myaccount";
		}
		
		
		AzoStore.ModelPackage.User user = new AzoStore.ModelPackage.User();
		user.setUsername(username);
		user.setEmail(userEmail);
		
		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole (user, role));
		userService.createUser(user, userRoles);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl ="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		
		
		SimpleMailMessage email = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(email);
		
		model.addAttribute("emailSent", "true");
		
		return "myaccount";
		
		
		
		
	}
	
	
	
}
