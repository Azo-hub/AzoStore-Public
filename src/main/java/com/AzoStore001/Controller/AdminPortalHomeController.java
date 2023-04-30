package com.AzoStore001.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminportal")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
public class AdminPortalHomeController {
	
	@GetMapping
	public String adminHome() {
		
		return "adminhome";
	}


}
