package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore1.ModelPackage.Order;




public interface OrderRepository extends JpaRepository<Order, Long> {
	
	

}
