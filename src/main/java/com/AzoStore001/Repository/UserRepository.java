package com.AzoStore001.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.AzoStore001.Model.User;


@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	
	User findByUsername (String username);
	
	User findByEmail (String email);
	
	
	@Transactional
	@Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.username = ?2")
	@Modifying
	public void updateFailedAttempt(long failedAttempt, String username);


	

}
