package AzoStore.ControllerPackage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.Product;
import AzoStore.ModelPackage.ProductToCartItem;
import AzoStore.ModelPackage.ShoppingCart;
import AzoStore.ModelPackage.User;
import AzoStore.ServicePackage.CartItemService;
import AzoStore.ServicePackage.ProductService;
import AzoStore.ServicePackage.ShoppingCartService;
import AzoStore.ServicePackage.UserService;



@Controller
@RequestMapping("/shoppingCart")

public class ShoppingCartController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/cart")
	public String shoppingCart (Model model, Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		//ProductToCartItem productToCartItem =  new ProductToCartItem(); 
		
		List <CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		if (cartItemList.size() == 0) {
			
			return "emptyCart";
		}
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		//model.addAttribute("productToCartItemid",productToCartItem);
		
		return "shoppingcart";
		
	}
	
	
	
	
	@PostMapping("/addItem")
	public String addItem(@ModelAttribute("product") Product product, @ModelAttribute("qty") String qty, Model model, Principal principal
			, RedirectAttributes redirectAttributes) {
		
		User user = userService.findByUsername(principal.getName());
		
		product = productService.getOne(product.getId());
		
		if (Integer.parseInt(qty) > product.getInStockNumber()) {
			model.addAttribute("notEnoughStock", true);
			return "forward:/productDetail?id="+product.getId();
		}
			else {
		
		CartItem cartItem = cartItemService.addProductToCartItem(product, user, Integer.parseInt(qty));
		
		model.addAttribute("cartItem",cartItem);
		
		String addProductSuccess = "Product Added to Shopping Cart";
		
		redirectAttributes.addFlashAttribute("addProductSuccess", addProductSuccess);
		
		return      "redirect:/viewproductdetail/"+ product.getId();   /* "redirect:/productDetail?id="+product.getId(); */
	
			}
	
	}
	
	
	@PostMapping("/updateCartItem")
	public String updateCart (@ModelAttribute ("cartItem") CartItem cartItem,
			@ModelAttribute("id") Long id,
			@ModelAttribute("qty") String qty, Model model, Principal principal) {
		
		
		User user = userService.findByUsername(principal.getName());
		
		cartItem = cartItemService.findById(cartItem.getId());
		 
		cartItem.setQty(Integer.parseInt(qty));
		 
		 
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		
		List <CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", shoppingCart);
		
		 
		 model.addAttribute("cartItem", cartItem);
		 
		 return "shoppingcart";
	}
	
	
	
	
	
	@GetMapping("/deleteCartItem/{id}")
	public String deleteCartItem (
			@PathVariable(name = "id") Long id,
			
			@ModelAttribute("cartItem") CartItem cartItem
			
			) {
		
		//cartItem = cartItemService.findById(id);
		
		cartItemService.removeproductToCartItem(id+1);
		//cartItemService.removeCartItem(id);
		return "redirect:/shoppingCart/cart";
		
				
		
	}
	
	
	
	
	
	
	
	
}
		
		
		
		