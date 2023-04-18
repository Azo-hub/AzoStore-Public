package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.ModelPackage.ProductToCartItem;



public interface ProductToCartItemRepository extends JpaRepository <ProductToCartItem, Long> {

	
}
