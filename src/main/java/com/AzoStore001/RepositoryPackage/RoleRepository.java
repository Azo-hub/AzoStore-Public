package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.ModelPackage.Role;




public interface RoleRepository extends JpaRepository <Role, Long> {
	Role findByName (String name);

}
