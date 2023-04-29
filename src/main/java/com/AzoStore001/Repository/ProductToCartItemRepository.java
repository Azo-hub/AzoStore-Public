package com.AzoStore001.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ProductToCartItem;

@Repository
@Transactional
public interface ProductToCartItemRepository extends JpaRepository <ProductToCartItem, Long> {

	void deleteByProduct(Product product);

	//void deleteByCartItem(Long id);
	

}
