package com.AzoStore001.CustomHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.AzoStore001.Model.User;
import com.AzoStore001.Service.UserService;
import com.AzoStore001.Service.UserServiceImpl;


@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String username = request.getParameter("username");
		User user = userService.findByUsername(username);
		
		if (user != null) {
			if (user.isEnabled() && user.isAccountNonLocked()) {
				if(user.getFailedAttempt() < UserServiceImpl.MAX_FAILED_ATTEMPTS - 1) {
					userService.increaseFailedAttempt(user);
				} 
				else {
					userService.lock(user);
					exception = new LockedException(
							"Your account has been locked due to 3 failed attempts. It will be unlocked after 24 hours.");
				}
					
			} else if (!user.isAccountNonLocked()) {
				
				if (userService.unlock(user)) {
					exception = new LockedException ("Your account has been unlocked. Please try to login again.");
				}
			}
		}
		
		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
	}

	
}
