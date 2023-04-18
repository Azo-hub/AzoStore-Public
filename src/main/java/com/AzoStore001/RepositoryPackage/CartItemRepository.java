package com.AzoStore001.RepositoryPackage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.ModelPackage.CartItem;
import com.AzoStore001.ModelPackage.Order;
import com.AzoStore001.ModelPackage.ShoppingCart;



public interface CartItemRepository extends JpaRepository <CartItem, Long> {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	void deleteByProduct(Long id);

	

	List<CartItem> findByOrder(Order order);

}
