package com.AzoStore001.ControllerPackage;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AzoStore1.ModelPackage.SecurityUtility;
import AzoStore1.ModelPackage.User;
import AzoStore1.RepositoryPackage.UserRepository;
import AzoStore1.ServicePackage.UserSecurityService;
import AzoStore1.ServicePackage.UserService;



@Controller
public class UpdateUserInfoController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
		

	@PostMapping ("/updateUserInfo") 
	public String updateUserInfo(@ModelAttribute("user") User user,
			@ModelAttribute("newPassword") String newPassword,
			@ModelAttribute("confirmPassword") String confirmPassword,
			Model model, RedirectAttributes redirectAttribute) throws Exception {
		
		User currentUser =  userService.findById(user.getId());
		
		if (currentUser == null) {
			throw new Exception ("User not found");
			
		} 
		
		/* check email already exists */
		
		if(userService.findByEmail(user.getEmail()) != null) {
			if(userService.findByEmail(user.getEmail()).getId() != currentUser.getId()) {
				model.addAttribute("emailExists", true);
				
				model.addAttribute("classActiveEdit", true);
				return "myprofile";
			}
			
		}
		
		
		/* check username already exists */
		
		if(userService.findByUsername(user.getUsername()) != null) {
			if(userService.findByUsername(user.getUsername()).getId() != currentUser.getId()) {
				model.addAttribute("UsernameExists", true);
				
				model.addAttribute("classActiveEdit", true);
				return "myprofile";
			}
			
		}
		
		
		/* update password */
		
		if (newPassword.matches(confirmPassword)) {
			
		} else {
			
			//String PassNotMatch = "Password Not Match";
			model.addAttribute("classActiveEdit", true);
			model.addAttribute("PasswordNotMatch", true);
			return "myprofile";
		}
		
		
		if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			if(passwordEncoder.matches(user.getPassword(), dbPassword)) {
				currentUser.setPassword(passwordEncoder.encode(newPassword));
				
			} else {
				model.addAttribute("classActiveEdit", true);
				model.addAttribute("incorrectPassword", true);
				
				return "myprofile";
				
			}
			
			
		}
		
		
		currentUser.setFirstname(user.getFirstname());
		currentUser.setLastname(user.getLastname());
		currentUser.setEmail(user.getEmail());
		
		
		userService.save(currentUser);
		
		redirectAttribute.addFlashAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);
		model.addAttribute("classActiveEdit", true);
		model.addAttribute("listOfShippingAddresses", true);
		
		model.addAttribute("displayOrderDetail", true);
		model.addAttribute("listOfCreditCard", true);
		
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddress", true);

		
		
		UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken (userDetails, userDetails.getPassword(), userDetails.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/myProfile";
		

		
		
	}
	

	
	
	

}
