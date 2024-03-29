package com.AzoStore001.CustomHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.AzoStore001.Model.User;
import com.AzoStore001.Service.UserService;


@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		User user = (User) authentication.getPrincipal();
		
		if (user.getFailedAttempt() > 0) {
			
			userService.resetFailedAttempts(user.getUsername());
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
