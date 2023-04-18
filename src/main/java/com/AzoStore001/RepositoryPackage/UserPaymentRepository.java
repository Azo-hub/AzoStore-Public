package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.ModelPackage.UserPayment;




public interface UserPaymentRepository extends JpaRepository <UserPayment, Long> {
	

}
