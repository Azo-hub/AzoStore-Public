package AzoStore.ServicePackage;

import java.util.List;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.Order;
import AzoStore.ModelPackage.Product;
import AzoStore.ModelPackage.ShoppingCart;
import AzoStore.ModelPackage.User;


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
