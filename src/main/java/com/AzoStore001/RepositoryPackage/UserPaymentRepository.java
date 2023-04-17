package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore1.ModelPackage.UserPayment;




public interface UserPaymentRepository extends JpaRepository <UserPayment, Long> {
	

}
