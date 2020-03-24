package com.springboot.amazonclone.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.amazonclone.entity.User;

public interface UserRepository extends MongoRepository<User, Integer> {
	
	User findByName(String name);

}
