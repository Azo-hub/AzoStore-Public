package com.AzoStore001.ServicePackage;

import java.util.List;

import com.AzoStore001.ModelPackage.CartItem;
import com.AzoStore001.ModelPackage.Order;
import com.AzoStore001.ModelPackage.Product;
import com.AzoStore001.ModelPackage.ShoppingCart;
import com.AzoStore001.ModelPackage.User;



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
