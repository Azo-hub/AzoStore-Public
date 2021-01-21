package AzoStore.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.Product;
import AzoStore.ModelPackage.ProductToCartItem;


public interface ProductToCartItemRepository extends JpaRepository <ProductToCartItem, Long> {

	//void deleteByCartItem(Long id);
	

}
