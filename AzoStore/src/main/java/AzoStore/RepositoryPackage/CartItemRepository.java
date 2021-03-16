package AzoStore.RepositoryPackage;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.Order;
import AzoStore.ModelPackage.Product;
import AzoStore.ModelPackage.ShoppingCart;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository <CartItem, Long> {
	
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	void deleteByProduct(Long id);

	List<CartItem> findByOrder(Order order);

	void deleteByProduct(Product product);

}
