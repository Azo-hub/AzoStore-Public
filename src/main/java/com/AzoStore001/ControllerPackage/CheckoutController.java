package com.AzoStore001.ControllerPackage;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import AzoStore1.ModelPackage.BillingAddress;
import AzoStore1.ModelPackage.CartItem;
import AzoStore1.ModelPackage.MailConstructor;
import AzoStore1.ModelPackage.NGConstants;
import AzoStore1.ModelPackage.Order;
import AzoStore1.ModelPackage.Payment;
import AzoStore1.ModelPackage.ShippingAddress;
import AzoStore1.ModelPackage.ShoppingCart;
import AzoStore1.ModelPackage.User;
import AzoStore1.ModelPackage.UserBilling;
import AzoStore1.ModelPackage.UserPayment;
import AzoStore1.ModelPackage.UserShipping;
import AzoStore1.ServicePackage.BillingAddressService;
import AzoStore1.ServicePackage.CartItemService;
import AzoStore1.ServicePackage.OrderService;
import AzoStore1.ServicePackage.PaymentService;
import AzoStore1.ServicePackage.ShippingAddressService;
import AzoStore1.ServicePackage.ShoppingCartService;
import AzoStore1.ServicePackage.UserPaymentService;
import AzoStore1.ServicePackage.UserService;
import AzoStore1.ServicePackage.UserShippingService;




@Controller
public class CheckoutController {
	
	private ShippingAddress shippingAddress = new ShippingAddress();
	private BillingAddress billingAddress = new BillingAddress();
	private Payment payment = new Payment ();
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@Autowired
	private BillingAddressService billingAddressService;
	
	
	@Autowired
	private UserShippingService userShippingService;
	
	
	@Autowired
	private UserPaymentService userPaymentService;
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MailConstructor mailConstructor;
	
	
	@GetMapping("/checkout")
	public String checkout(
			@RequestParam("id") Long cartId,
			@RequestParam(value ="missingRequiredField", required = false) boolean missingRequiredField,
			Model model, Principal principal) {
		
		User user = userService.findByUsername(principal.getName());
		
		if(cartId != user.getShoppingCart().getId()) {
			return "badRequestPage";
			
		}
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
		
		if(cartItemList.size() == 0) {
			model.addAttribute("emptyCart", true);
			return "forward:/shoppingCart/cart";
		}
		
		for (CartItem cartItem : cartItemList) {
			if (cartItem.getProduct().getInStockNumber() < cartItem.getQty()) {
				model.addAttribute("notEnoughStock", true);
				return "forward:/shoppingCart/cart";
			}
		}
		
		
		List <UserShipping> userShippingList = user.getUserShippingList();
		List<UserPayment> userPaymentList = user.getUserPaymentList();
		
		model.addAttribute("userShippingList", userShippingList);
		model.addAttribute("userPaymentList", userPaymentList);
		
		if (userPaymentList.size() == 0) {
			model.addAttribute("emptyPaymentList", true);
			
		} else {
			model.addAttribute("emptyPaymentList", false);
			
		}
		
		
		if (userShippingList.size() == 0) {
			model.addAttribute("emptyShippingList", true);
			
		} else {
			model.addAttribute("emptyShippingList", false);
			
		}
		
		
		ShoppingCart shoppingCart = user.getShoppingCart();
		String userEmail = shoppingCart.getUser().getEmail();
		String userFirstName = shoppingCart.getUser().getFirstname();
		String userLastName = shoppingCart.getUser().getLastname();
		
		for(UserShipping userShipping : userShippingList) {
			if (userShipping.isUserShippingDefault()) {
				shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			}
		}
		
		
		for(UserPayment userPayment : userPaymentList) {
			if (userPayment.isDefaultPayment()) {
				paymentService.setByUserPayment(userPayment, payment);
				billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
			}
		}
		
		
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("payment", payment);
		model.addAttribute("billingAddress", billingAddress);
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("shoppingCart", user.getShoppingCart());
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("userFirstName", userFirstName);
		model.addAttribute("userLastName", userLastName);
		
		List <String> stateList = NGConstants.listOfNGStatesName;
		Collections.sort(stateList);
		model.addAttribute("stateList", stateList);
		
		model.addAttribute("classActiveShipping", true);
		model.addAttribute("user", user);
		
		if(missingRequiredField) {
			model.addAttribute("missingRequiredField", true);
			
		}
		
		return "checkout";
	}

	
	
	
	
	
	
	@PostMapping("/checkout")
	public String checkoutPost(
			@ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
			//@ModelAttribute("billingAddress") BillingAddress billingAddress,
			//@ModelAttribute("payment") Payment payment,
			//@ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
			
			@ModelAttribute("shippingMethod") String shippingMethod,
			Principal principal,
			Model model) {
		
		ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("cartItemList", cartItemList);
		
		
		/* if (billingSameAsShipping.equals("true")) {
			
		 *	billingAddress.setBillingAddressName(shippingAddress.getShippingAddressCity());
		*	billingAddress.setBillingAddressStreet1(shippingAddress.getShippingAddressStreet1());
		*	billingAddress.setBillingAddressStreet2(shippingAddress.getShippingAddressStreet2());
		*	billingAddress.setBillingAddressCity(shippingAddress.getShippingAddressCity());
		*	billingAddress.setBillingAddressState(shippingAddress.getShippingAddressState());
		*	billingAddress.setBillingAddressCountry(shippingAddress.getShippingAddressCountry());
		*	billingAddress.setBillingAddressZipCode(shippingAddress.getShippingAddressZipCode());
		*	
		* }
		
		*/
		
		  if(shippingAddress.getShippingAddressStreet1().isEmpty() || 
				shippingAddress.getShippingAddressCity().isEmpty() || 
				shippingAddress.getShippingAddressState().isEmpty() ||
				shippingAddress.getShippingAddressName().isEmpty() ||
				shippingAddress.getShippingAddressZipCode().isEmpty() ||
				payment.getCardNumber().isEmpty() ||
				payment.getCvc() == 0 ||
				billingAddress.getBillingAddressStreet1().isEmpty() ||
				billingAddress.getBillingAddressCity().isEmpty() ||
				billingAddress.getBillingAddressName().isEmpty() ||
				billingAddress.getBillingAddressZipCode().isEmpty()) {
			
			return "redirect:/checkout?id=" + shoppingCart.getId()+"&missingRequiredField=true";
			
		}
		
		User user = userService.findByUsername(principal.getName());
		//Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);
		
		Order order = orderService.createOrder(shoppingCart, shippingAddress, shippingMethod, user);
		
		mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		shoppingCartService.clearShoppingCart(shoppingCart);
		
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate;
		
		
		if(shippingMethod.equals("groundShipping")) {
			estimatedDeliveryDate = today.plusDays(5);
		} else {
			
			estimatedDeliveryDate = today.plusDays(3);
		}
		
		model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);
		
		return "orderSubmittedPage";
			
		
	}
	
	
	
	
	
	@GetMapping("/setShippingAddress")
	public String setShippingAddress (
			@RequestParam("userShippingId") Long userShippingId,
			Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		UserShipping userShipping = userShippingService.getOne(userShippingId);
		
		if(userShipping.getUser().getId() != user.getId()) {
			return "badRequestPage";
			
		} else {
			shippingAddressService.setByUserShipping(userShipping, shippingAddress);
			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			//BillingAddress billingAddress = new BillingAddress();
			
			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("payment", payment);
			model.addAttribute("billingAddress", billingAddress);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", user.getShoppingCart());
			
			
			List <String> stateList = NGConstants.listOfNGStatesName;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			List <UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();
			
			model.addAttribute("userShippingList", userShippingList);
			model.addAttribute("userPaymentList", userPaymentList);
			
			model.addAttribute("shippingAddress", shippingAddress);
			
			model.addAttribute("classActiveShipping", true);
			
			if (userPaymentList.size() == 0) {
				model.addAttribute("emptyPaymentList", true);
				
			} else {
				model.addAttribute("emptyPaymentList", false);
				
			}
			
			
			
				model.addAttribute("emptyShippingList", false);
				
		
			
			
			return "checkout";
			
		}
		
	}
	
	
	
	
	
	@GetMapping("/setPaymentMethod")
	public String setPaymentMethod(
			@RequestParam("userPaymentId") Long userPaymentId,
			Principal principal, Model model) {
		
		
		User user = userService.findByUsername(principal.getName());
		UserPayment userPayment = userPaymentService.getOne(userPaymentId);
		UserBilling userBilling = userPayment.getUserBilling();
		
		if(userPayment.getUser().getId() != user.getId()) {
			return "badRequestPage";
			
		} else {
			paymentService.setByUserPayment(userPayment, payment);
			
			List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
			billingAddressService.setByUserBilling(userBilling, billingAddress);
			
			model.addAttribute("user", user);
			model.addAttribute("shippingAddress", shippingAddress);
			model.addAttribute("payment", payment);
			model.addAttribute("billingAddress", billingAddress);
			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", user.getShoppingCart());
			
			
			List <String> stateList = NGConstants.listOfNGStatesName;
			Collections.sort(stateList);
			model.addAttribute("stateList", stateList);
			
			List <UserShipping> userShippingList = user.getUserShippingList();
			List<UserPayment> userPaymentList = user.getUserPaymentList();
			
			model.addAttribute("userShippingList", userShippingList);
			model.addAttribute("userPaymentList", userPaymentList);
			
			model.addAttribute("shippingAddress", shippingAddress);
			
			model.addAttribute("classActivePayment", true);
			
			
			
				model.addAttribute("emptyPaymentList", false);
				
			
			
			
			if (userShippingList.size() == 0) {
				model.addAttribute("emptyShippingList", true);
				
			} else {
				model.addAttribute("emptyShippingList", false);
				
			}
			
			
			return "checkout";
		
		
		
	}
		
	}
	
	
	
	
}