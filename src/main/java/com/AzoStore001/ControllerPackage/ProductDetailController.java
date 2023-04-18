package com.AzoStore001.ControllerPackage;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.AzoStore001.ModelPackage.Product;
import com.AzoStore001.ModelPackage.User;
import com.AzoStore001.ServicePackage.ProductService;
import com.AzoStore001.ServicePackage.UserService;



@Controller

public class ProductDetailController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/viewproductdetail/{id}")
	public String productDetails(
			@ModelAttribute("addProductSuccess") String addProductSuccess,
			@PathVariable(name = "id") Long id, Model model,  Principal principal) {
		if (principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
			
			/* ===== To get the number of cartItem in the shopping cart ====== */
			  Integer Count = user.getShoppingCart().getCartItemList().size();
			  model.addAttribute("Count", Count);
			
		}
		
		Product product = productService.getOne(id);
		model.addAttribute("product", product);
		
		List <Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
		
		model.addAttribute("qtyList", qtyList);
		model.addAttribute("qty", 1);
		
		
			model.addAttribute("addProductSuccess", addProductSuccess);
			
		
		
		
		return "productInfo";
	}

}
