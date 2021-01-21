package AzoStore.RepositoryPackage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.Order;
import AzoStore.ModelPackage.ShoppingCart;


public interface CartItemRepository extends JpaRepository <CartItem, Long> {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	void deleteByProduct(Long id);

	

	List<CartItem> findByOrder(Order order);

}
