package com.AzoStore001.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.User;
import com.AzoStore001.Service.ProductService;
import com.AzoStore001.Service.UserService;


@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/searchByCategory")
	public String searchByCategory (
			@RequestParam("category") String category,
			Model model, Principal principal) {
		
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
			
			/* ===== To get the number of cartItem in the shopping cart ====== */
			  Integer Count = user.getShoppingCart().getCartItemList().size();
			  model.addAttribute("Count", Count);
		}
		
		String classActiveCategory = "active" + category;
		classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
		classActiveCategory = classActiveCategory.replaceAll("&", "");
		model.addAttribute(classActiveCategory, true);
		
		List<Product> productList = productService.findByCategory(category);
		
		if (productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			
			return "home";
		}
		
		model.addAttribute("productList", productList);
		
		return "home";
	}
	
	
	
	
	@PostMapping ("/searchProduct")
	public String searchBook(
			@ModelAttribute ("keyword") String keyword,
			Principal principal, Model model) {
		
		if(principal != null) {
			String username = principal.getName();
			User user = userService.findByUsername(username);
			model.addAttribute("user", user);
			
		}
		
		List <Product> productList = productService.blurrySearch(keyword);
		
		if(productList.isEmpty()) {
			model.addAttribute("emptyList", true);
			
			return "home";
		}
		
		model.addAttribute("productList", productList);
		
		return "home";
	}




}
