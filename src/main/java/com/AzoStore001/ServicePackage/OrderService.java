package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.BillingAddress;
import AzoStore1.ModelPackage.Order;
import AzoStore1.ModelPackage.Payment;
import AzoStore1.ModelPackage.ShippingAddress;
import AzoStore1.ModelPackage.ShoppingCart;
import AzoStore1.ModelPackage.User;

public interface OrderService {
	/* Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
			BillingAddress billingAddress, Payment payment, String shippingMethod, User user); */
	
	
	
	Order getOne(Long id);

	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, String shippingMethod, User user);

}
