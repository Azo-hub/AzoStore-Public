package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.ModelPackage.ShoppingCart;



public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

	
}
