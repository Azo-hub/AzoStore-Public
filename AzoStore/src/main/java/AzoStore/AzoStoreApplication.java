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
public class AzoStoreApplication implements CommandLineRunner {
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
	
	
	@Override
	public void run(String... args) throws Exception {
			
			User user1 = new User();
			user1.setFirstname("Ridwan");
			user1.setLastname("Azeez");
			user1.setUsername("r");
			user1.setPassword(SecurityUtility.passwordEncoder().encode("r"));
			user1.setEmail("readone.cybernet@gmail.com");
			Set<UserRole> userRoles = new HashSet<>();
			Role role1 = new Role();
			role1.setRoleId(1);
			role1.setName("ROLE_USER");
			userRoles.add(new UserRole(user1, role1));
			
			userService.createUser(user1, userRoles);
		}
		

	
		
	 
	

}
