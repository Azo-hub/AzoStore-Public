package com.AzoStore001.ServicePackage;

import com.AzoStore001.ModelPackage.Order;
import com.AzoStore001.ModelPackage.ShippingAddress;
import com.AzoStore001.ModelPackage.ShoppingCart;
import com.AzoStore001.ModelPackage.User;

public interface OrderService {
	
	
	Order getOne(Long id);

	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, String shippingMethod, User user);

}
