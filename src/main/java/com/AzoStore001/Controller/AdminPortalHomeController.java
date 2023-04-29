package com.AzoStore001.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminportal")
public class AdminPortalHomeController {
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@GetMapping
	public String adminHome() {
		
		return "adminhome";
	}
	
	
	

}
