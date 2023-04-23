package com.AzoStore001.Controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		model.addAttribute("classActiveLogin", true);
		
		return "myaccount";
	}

}
