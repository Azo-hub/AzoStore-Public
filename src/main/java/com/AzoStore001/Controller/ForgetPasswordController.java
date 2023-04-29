package com.AzoStore001.Controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.AzoStore001.Model.MailConstructor;
import com.AzoStore001.Model.SecurityUtility;
import com.AzoStore001.Model.User;
import com.AzoStore001.Service.UserService;

@Controller
public class ForgetPasswordController {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	MailConstructor mailConstructor;
	
	@Autowired
	UserService userService;

	@PostMapping("/forgetPassword")
	public String forgetPassword(HttpServletRequest request, @ModelAttribute("email") String email,
			 Model model) {

		model.addAttribute("classActiveForgetPassword", true);
		
		User user = userService.findByEmail(email);
		
		if (user == null) {
			
			model.addAttribute("emailNotExist", true);
			return "myaccount";
			
		}
		
		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		
		userService.save(user);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		String appUrl ="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		
		
		
		SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);
		
		mailSender.send(newEmail);
		
		model.addAttribute("forgetPasswordEmailSent", "true");
	
		
		
		return "myaccount";
	}

}
