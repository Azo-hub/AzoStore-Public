package AzoStore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cloudinary.SingletonManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import AzoStore.ModelPackage.Role;
import AzoStore.ModelPackage.SecurityUtility;
import AzoStore.ModelPackage.User;
import AzoStore.ModelPackage.UserRole;
import AzoStore.ServicePackage.UserService;

@SpringBootApplication
@EnableScheduling
public class AzoStoreApplication {
	@Autowired
	private UserService userService;
	

	
	
	

	public static void main(String[] args) {
		
		// Set Cloudinary instance
	    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	            "cloud_name", "azostore", // insert here you cloud name
	            "api_key", "873821249613849", // insert here your api code
	            "api_secret", "GPu-lx7TZYpi8VXxeo-8mvW0ri4")); // insert here your api secret
	    		SingletonManager manager = new SingletonManager();
	    		manager.setCloudinary(cloudinary);
	    		manager.init();
			
	    		
		SpringApplication.run(AzoStoreApplication.class, args);
	}
	
	
		

	
		
	 
	

}
