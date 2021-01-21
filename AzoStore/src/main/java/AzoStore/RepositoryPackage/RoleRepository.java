package AzoStore.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.Role;


public interface RoleRepository extends JpaRepository <Role, Long> {
	Role findByName (String name);

}
