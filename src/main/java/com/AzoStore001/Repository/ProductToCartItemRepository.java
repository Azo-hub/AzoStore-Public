package com.AzoStore001.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ProductToCartItem;



public interface ProductToCartItemRepository extends JpaRepository <ProductToCartItem, Long> {

	void deleteByProduct(Product product);

}
