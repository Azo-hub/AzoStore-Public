package com.AzoStore001.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.CartItem;
import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ShoppingCart;



public interface CartItemRepository extends JpaRepository <CartItem, Long> {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	void deleteByProduct(Product product);

	

	List<CartItem> findByOrder(Order order);

}
