package com.AzoStore001.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.User;





public interface UserRepository extends JpaRepository <User, Long> {
	
	User findByUsername (String username);
	
	User findByEmail (String email);

	

}
