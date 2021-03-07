package AzoStore.ControllerPackage;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import AzoStore.ModelPackage.Product;
import AzoStore.ModelPackage.User;
import AzoStore.ServicePackage.ProductService;
import AzoStore.ServicePackage.UserService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String productList(Model model, Principal principal, HttpServletRequest request) {
		 if (principal != null) {
		 String username = principal.getName();
		 User user = userService.findByUsername(username);
		 model.addAttribute("user", user);
		 
		 /* ===== To get the number of cartItem in the shopping cart ====== */
		 
		 Integer Count = user.getShoppingCart().getCartItemList().size();
		 model.addAttribute("Count", Count);
		 
		 }
		 
		// Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				
		 //	if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
					
		 
		  
		 	//}
		 
		 	
		  
		  	
			
		List<Product> productList = productService.findAll();
		Collections.shuffle(productList);
		model.addAttribute("productList", productList);
		model.addAttribute("activeAll", true);

		return "home";

	}

}
