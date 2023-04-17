package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore1.ModelPackage.User;





public interface UserRepository extends JpaRepository <User, Long> {
	
	User findByUsername (String username);
	
	User findByEmail (String email);

	

}
