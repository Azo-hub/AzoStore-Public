package com.AzoStore001.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.ShoppingCart;



public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

	
}
