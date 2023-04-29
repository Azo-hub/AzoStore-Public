package com.AzoStore001.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.UserPayment;


public interface UserPaymentRepository extends JpaRepository <UserPayment, Long> {
	

}
