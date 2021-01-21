package AzoStore.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.User;



public interface UserRepository extends JpaRepository <User, Long> {
	
	User findByUsername (String username);
	
	User findByEmail (String email);

	

}
