package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore1.ModelPackage.ShoppingCart;



public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

	
}
