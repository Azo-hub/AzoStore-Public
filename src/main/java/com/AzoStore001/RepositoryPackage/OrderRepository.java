package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.ModelPackage.Order;




public interface OrderRepository extends JpaRepository<Order, Long> {
	
	

}
