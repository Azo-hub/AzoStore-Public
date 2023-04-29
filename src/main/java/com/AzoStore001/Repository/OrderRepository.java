package com.AzoStore001.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	
	

}
