package com.AzoStore001.Service;

import java.util.List;

import com.AzoStore001.Model.CartItem;
import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ShoppingCart;
import com.AzoStore001.Model.User;


public interface CartItemService {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addProductToCartItem(Product product, User user, int qty);

	CartItem save(CartItem cartItem);

	List<CartItem> findByOrder(Order order);
	
	CartItem findById(Long id);
	
	void removeCartItem(Long id);

	void removeproductToCartItem(Long id);
	
	
	
	

}
