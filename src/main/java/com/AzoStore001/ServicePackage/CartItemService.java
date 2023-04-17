package com.AzoStore001.ServicePackage;

import java.util.List;

import AzoStore1.ModelPackage.CartItem;
import AzoStore1.ModelPackage.Order;
import AzoStore1.ModelPackage.Product;
import AzoStore1.ModelPackage.ShoppingCart;
import AzoStore1.ModelPackage.User;



public interface CartItemService {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addProductToCartItem(Product product, User user, int qty);

	CartItem save(CartItem cartItem);

	List<CartItem> findByOrder(Order order);
	
	CartItem findById(Long id);
	
	//void removeCartItem(CartItem cartItem);

	void removeCartItem(Long id);

	void removeproductToCartItem(Long id);
	
	
	
	

}
