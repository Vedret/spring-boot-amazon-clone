package com.springboot.amazonclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.amazonclone.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	  Role findByRole(String role);

}
