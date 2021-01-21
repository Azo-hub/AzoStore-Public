package AzoStore.ControllerPackage;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.NGConstants;
import AzoStore.ModelPackage.Order;
import AzoStore.ModelPackage.SecurityUtility;
import AzoStore.ModelPackage.User;
import AzoStore.ModelPackage.UserBilling;
import AzoStore.ModelPackage.UserPayment;
import AzoStore.ModelPackage.UserShipping;
import AzoStore.ServicePackage.CartItemService;
import AzoStore.ServicePackage.OrderService;
import AzoStore.ServicePackage.UserPaymentService;
import AzoStore.ServicePackage.UserService;
import AzoStore.ServicePackage.UserShippingService;



@Controller
public class MyProfileController {
	
	

	@Autowired
	private UserService userService;

	@Autowired
	private UserPaymentService userPaymentService;
	
	
	@Autowired
	private UserShippingService userShippingService;
	
	
	@Autowired
	private CartItemService cartItemService;
	
	
	@Autowired
	private OrderService orderService;
	
	
	

	@GetMapping("/myProfile")
	public String myProfile(Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());
		
		/* ===== To get the number of cartItem in the shopping cart ====== */
		  Integer Count = user.getShoppingCart().getCartItemList().size();
		  model.addAttribute("Count", Count);
		
		/*
		 * UserShipping userShipping = new UserShipping();
		 * model.addAttribute("userShipping", userShipping);
		 */

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("listOfShippingAddress", true);

		List<String> stateList = NGConstants.listOfNGStatesName;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("classActiveEdit", true);


		return "myprofile";
	}

	
	@GetMapping("/listOfCreditCard")
	public String listOfCreditCards(Model model, Principal principal /* ,HttpServletRequest request */) {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "myprofile";

	}

	
	@GetMapping("/addNewCreditCard")
	public String addNewCreditCard(Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		model.addAttribute("addNewCreditCard", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		

		UserBilling userBilling = new UserBilling();
		UserPayment userPayment = new UserPayment();

		model.addAttribute("userBilling", userBilling);
		model.addAttribute("userPayment", userPayment);

		List<String> stateList = NGConstants.listOfNGStatesName;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		return "myprofile";

	}

	@PostMapping("/addNewCreditCard")
	public String addNewCreditCardPost(@ModelAttribute("userPayment") UserPayment userPayment,
			@ModelAttribute("userBilling") UserBilling userBilling, Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		userService.updateUserBilling(userBilling, userPayment, user);

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("orderList", user.getOrderList());

		return "myprofile";

	}

	
	
	
	
	@GetMapping("/updateCreditCard")
	public String addNewCreditCard(@ModelAttribute("id") Long creditCardId, Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.getOne(creditCardId);

		if (user.getId() != userPayment.getUser().getId()) {
			return "badRequestPage";
		} else {

			model.addAttribute("user", user);
			UserBilling userBilling = userPayment.getUserBilling();
			model.addAttribute("userPayment", userPayment);
			model.addAttribute("userBilling", userBilling);
			
			
			List<String> stateList = NGConstants.listOfNGStatesName;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			model.addAttribute("addNewCreditCard", true);
			model.addAttribute("classActiveBilling", true);
			model.addAttribute("listOfShippingAddresses", true);

			return "myprofile";

		}

	}
	
	
	
	
	
	@GetMapping("/removeCreditCard")
	public String removeCreditCard(@ModelAttribute("id") Long creditCardId, Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.getOne(creditCardId);

		if (user.getId() != userPayment.getUser().getId()) {
			return "badRequestPage";
		} else {

			model.addAttribute("user", user);
			userPaymentService.removeById(creditCardId);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			model.addAttribute("listOfCreditCards", true);
			model.addAttribute("classActiveBilling", true);
			model.addAttribute("listOfShippingAddresses", true);

			return "myprofile";

		}

	}
	
	
	

	@PostMapping("/setDefaultPayment")
	public String setDefaultPayment(@ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal,
			Model model) {

		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultPayment(defaultPaymentId, user);

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList()); 

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveBilling", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "myprofile";

	}
	
	
	
	
	
	@GetMapping("/listOfShippingAddresses")
	public String listOfShippingAddresses(Model model, Principal principal /*, HttpServletRequest request */) {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "myprofile";

	}


	@GetMapping("/addNewShippingAddress")
	public String addnewShippingAddress(Model model, Principal principal) {

		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);

		model.addAttribute("addNewShippingAddress", true);
		model.addAttribute("classActiveShipping", true);
		/*model.addAttribute("listOfShippingAddresses", true);*/

		UserShipping userShipping = new UserShipping();

		model.addAttribute("userShipping", userShipping);

		List<String> stateList = NGConstants.listOfNGStatesName;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		//model.addAttribute("userShippingList", user.getUserShippingList());
		//model.addAttribute("orderList", user.getOrderList());

		return "myprofile";

	}

	
	
	@PostMapping("/addNewShippingAddress")
	public String addNewShippingAddressPost(@ModelAttribute("userShipping") UserShipping userShipping,
			 Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		userService.updateUserShipping(userShipping, user);

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);
		model.addAttribute("orderList", user.getOrderList());
		

		return "myprofile";

	}

	
	

	@GetMapping("/updateUserShipping")
	public String updateUserShipping(@ModelAttribute("id") Long shippingAddressId, Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.getOne(shippingAddressId);

		if (user.getId() != userShipping.getUser().getId()) {
			return "badRequestPage";
		} else {

			model.addAttribute("user", user);
			
			model.addAttribute("userShipping", userShipping);

			List<String> stateList = NGConstants.listOfNGStatesName;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			model.addAttribute("addNewShippingAddress", true);
			model.addAttribute("classActiveShipping", true);
			model.addAttribute("listOfCreditCard", true);

			return "myprofile";

		}

	}

	
	
	
	
	@PostMapping("/setDefaultShippingAddress")
	public String setDefaultShippingAddress(@ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal,
			Model model) {

		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultShipping(defaultShippingId, user);

		model.addAttribute("user", user);
		model.addAttribute("userPaymentList", user.getUserPaymentList());
		model.addAttribute("userShippingList", user.getUserShippingList());
		model.addAttribute("orderList", user.getOrderList());

		model.addAttribute("listOfCreditCards", true);
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("listOfShippingAddresses", true);

		return "myprofile";

	}

	
	
	
	
	@GetMapping("/removeUserShipping")
	public String removeUserShipping(@ModelAttribute("id") Long userShippingId, Principal principal, Model model) {

		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.getOne(userShippingId);

		if (user.getId() != userShipping.getUser().getId()) {
			return "badRequestPage";
		} else {

			model.addAttribute("user", user);
			userShippingService.removeById(userShippingId);

			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());

			/*model.addAttribute("listOfCreditCards", true);*/
			model.addAttribute("classActiveShipping", true);
			model.addAttribute("listOfShippingAddresses", true);

			return "myprofile";

		}

	}

	
	
	
	

	
	/*  Order Detail Controller  */
	
	@GetMapping("/orderDetail")
	public String orderDetail (
			@RequestParam("id") Long orderId,
			Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		Order order = orderService.getOne(orderId);
		
		
		if (order.getUser().getId() != user.getId()) {
			return "badRequestPage";
			
			
		} else {
			
			List<CartItem> cartItemList = cartItemService.findByOrder(order);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("user", user);
			model.addAttribute("order", order);
			
			model.addAttribute("userPaymentList", user.getUserPaymentList());
			model.addAttribute("userShippingList", user.getUserShippingList());
			model.addAttribute("orderList", user.getOrderList());
			
			UserShipping userShipping = new UserShipping();
			
			model.addAttribute("userShipping", userShipping);

			List<String> stateList = NGConstants.listOfNGStatesName;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);

			

			model.addAttribute("listOfShippingAddresses", true);
			model.addAttribute("classActiveOrder", true);
			model.addAttribute("displayOrderDetail", true);
			model.addAttribute("listOfCreditCard", true);

			return "myprofile";

			
		} 
		
	} 

}
