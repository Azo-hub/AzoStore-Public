package com.AzoStore001;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.AzoStore001.Model.MailConstructor;
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
public class AzoStoreApplication /* implements CommandLineRunner */ {
	
/*	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;
	
	@Autowired
	private UserService userService;   */


	public static void main(String[] args) {
		// Set Cloudinary instance
	    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	            "cloud_name", "********", // insert here you cloud name
	            "api_key", "**********", // insert here your api code
	            "api_secret", "***************")); // insert here your api secret
	    		SingletonManager manager = new SingletonManager();
	    		manager.setCloudinary(cloudinary);
	    		manager.init();
		
		
		SpringApplication.run(AzoStoreApplication.class, args);
	}
	
	
	
	
	 
  /*  @Override
    public void run(String ...args) throws Exception {
    	
    	//User newUser = userService.createUser("readone.cybernet@gmail.com", "readone", "ROLE_ADMIN");

		
		User user = new User();
		user.setUsername("readone");
		user.setEmail("readone.cybernet@gmail.com");
		
		String password = SecurityUtility.randomPassword();
		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		Role role = new Role();
		role.setRoleId(2);
		role.setName("ROLE_ADMIN");
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole (user, role));
		userService.createUser(user, userRoles);
		
		String token = UUID.randomUUID().toString();
		userService.createPasswordResetTokenForUser(user, token);
		
		
		SimpleMailMessage email = mailConstructor.constructResetTokenEmail("", null, "", user, password);
		
		mailSender.send(email);
		

    	
    }  */
    
    	

}
