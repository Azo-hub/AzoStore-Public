package com.AzoStore001.Service;

import com.AzoStore001.Model.BillingAddress;
import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.Payment;
import com.AzoStore001.Model.ShippingAddress;
import com.AzoStore001.Model.ShoppingCart;
import com.AzoStore001.Model.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
			BillingAddress billingAddress, Payment payment, String shippingMethod, User user);
	
	Order getOne(Long id);


}
