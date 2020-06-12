package com.springboot.amazonclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springboot.amazonclone.entity.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String> {
	
	

}
