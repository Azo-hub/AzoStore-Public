package com.AzoStore001.Service;

import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.ShippingAddress;
import com.AzoStore001.Model.ShoppingCart;
import com.AzoStore001.Model.User;

public interface OrderService {
	
	
	Order getOne(Long id);

	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, String shippingMethod, User user);

}
