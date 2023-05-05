package com.AzoStore001;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.AzoStore001.Model.Role;
import com.AzoStore001.Model.SecurityUtility;
import com.AzoStore001.Model.User;
import com.AzoStore001.Model.UserRole;
import com.AzoStore001.Service.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
@EnableScheduling
public class AzoStore2Application /* implements CommandLineRunner */ {
	
	
/*  @Autowired
	private UserService userService;  */
	
	

	public static void main(String[] args) {
		// Set Cloudinary instance
	    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	    		"cloud_name", "************", // insert here you cloud name
	            "api_key", "***************", // insert here your api code
	            "api_secret", "********************")); // insert here your api secret
	    		SingletonManager manager = new SingletonManager();
	    		manager.setCloudinary(cloudinary);
	    		manager.init();
		
		SpringApplication.run(AzoStore2Application.class, args);
	}
	
	
	
	/*
	@Override
	public void run(String... args) throws Exception {
		
		
		User adminUser = new User();
		adminUser.setFirstname("Ridwan");
		adminUser.setLastname("Azeez");
		adminUser.setUsername("readone");
		adminUser.setEnabled(true);
		adminUser.setAccountNonLocked(true);
		adminUser.setFailedAttempt((long) 0);
		adminUser.setPassword(SecurityUtility.passwordEncoder().encode("readone"));
		adminUser.setEmail("readone.cybernet@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role2 = new Role();
		role2.setRoleId(2);
		role2.setName("ADMIN");
		userRoles.add(new UserRole(adminUser, role2));
		
		userService.createUser(adminUser, userRoles);
		

		
	}


	
		*/


}
