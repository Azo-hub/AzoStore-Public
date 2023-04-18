package com.AzoStore001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class AzoStoreApplication {

	public static void main(String[] args) {
		// Set Cloudinary instance
	    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	            "cloud_name", "******", // insert here you cloud name
	            "api_key", "*********", // insert here your api code
	            "api_secret", "**************")); // insert here your api secret
	    		SingletonManager manager = new SingletonManager();
	    		manager.setCloudinary(cloudinary);
	    		manager.init();
		
		
		SpringApplication.run(AzoStoreApplication.class, args);
	}

}
