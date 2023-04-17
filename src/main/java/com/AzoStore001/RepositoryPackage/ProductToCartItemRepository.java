package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore1.ModelPackage.ProductToCartItem;



public interface ProductToCartItemRepository extends JpaRepository <ProductToCartItem, Long> {

	//void deleteByCartItem(Long id);
	

}
