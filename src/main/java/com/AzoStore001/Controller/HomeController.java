package com.AzoStore001.Controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.User;
import com.AzoStore001.Service.ProductService;
import com.AzoStore001.Service.UserService;


@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String productList(Model model, Principal principal) {
		 if (principal != null) {
		 String username = principal.getName();
		 User user = userService.findByUsername(username);
		 model.addAttribute("user", user);
		 
		 /* ===== To get the number of cartItem in the shopping cart ====== */
		  Integer Count = user.getShoppingCart().getCartItemList().size();
		  model.addAttribute("Count", Count);
		 }
		
		 
		   
		  
		  	
			
		List<Product> productList = productService.findAll();
		Collections.shuffle(productList);
		model.addAttribute("productList", productList);
		model.addAttribute("activeAll", true);

		return "home";

	}

}
