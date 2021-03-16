package AzoStore;


import java.util.HashSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		
		
		
		User editorUser = new User();
		editorUser.setFirstname("Ridwan");
		editorUser.setLastname("Azeez");
		editorUser.setUsername("editor1");
		editorUser.setEnabled(true);
		editorUser.setAccountNonLocked(true);
		editorUser.setFailedAttempt((long) 0);
		editorUser.setPassword(SecurityUtility.passwordEncoder().encode("editor1"));
		editorUser.setEmail("haycodeeditor101@gmail.com");
		Set<UserRole> userRoles1 = new HashSet<>();
		Role role2 = new Role();
		role2.setRoleId(2);
		role2.setName("EDITOR");
		userRoles1.add(new UserRole(editorUser, role2));
		
		userService.createUser(editorUser, userRoles1);
		

		
		
		
		User adminUser = new User();
		adminUser.setFirstname("Ridwan");
		adminUser.setLastname("Azeez");
		adminUser.setUsername("admin1");
		adminUser.setEnabled(true);
		adminUser.setAccountNonLocked(true);
		adminUser.setFailedAttempt((long) 0);
		adminUser.setPassword(SecurityUtility.passwordEncoder().encode("admin1"));
		adminUser.setEmail("haycodea843@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(3);
		role1.setName("ADMIN");
		userRoles.add(new UserRole(adminUser, role1));
		
		userService.createUser(adminUser, userRoles);
		
		
		
		
		
				
	}


	
		
	 
	

}
