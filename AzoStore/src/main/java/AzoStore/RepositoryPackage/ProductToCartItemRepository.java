package AzoStore.RepositoryPackage;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AzoStore.ModelPackage.CartItem;
import AzoStore.ModelPackage.Product;
import AzoStore.ModelPackage.ProductToCartItem;

@Repository
@Transactional
public interface ProductToCartItemRepository extends JpaRepository <ProductToCartItem, Long> {

	void deleteByProduct(Product product);

	//void deleteByCartItem(Long id);
	

}
