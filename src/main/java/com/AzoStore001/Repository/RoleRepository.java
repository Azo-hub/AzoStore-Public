package com.AzoStore001.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AzoStore001.Model.Role;


public interface RoleRepository extends JpaRepository <Role, Long> {
	Role findByName (String name);

}
