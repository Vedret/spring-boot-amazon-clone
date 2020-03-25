package com.springboot.amazonclone.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.amazonclone.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByFullName(String name);
	User findByEmail(String email);

}
