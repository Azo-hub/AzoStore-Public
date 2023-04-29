package com.AzoStore001.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AzoStore001.Model.CartItem;
import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ShoppingCart;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository <CartItem, Long> {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	void deleteByProduct(Long id);

	List<CartItem> findByOrder(Order order);

	void deleteByProduct(Product product);

}
