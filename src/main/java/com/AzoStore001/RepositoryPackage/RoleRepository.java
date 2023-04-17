package com.AzoStore001.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore1.ModelPackage.Role;




public interface RoleRepository extends JpaRepository <Role, Long> {
	Role findByName (String name);

}
