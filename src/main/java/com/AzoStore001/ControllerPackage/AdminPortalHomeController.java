package com.AzoStore001.ControllerPackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminportal")
public class AdminPortalHomeController {
	
	@GetMapping
	public String adminHome() {
		
		return "adminhome";
	}
	
	
	

}
