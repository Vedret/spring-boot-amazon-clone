package com.springboot.amazonclone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.springboot.amazonclone.entity.Category;



public interface  CategoryRepository extends MongoRepository<Category, String> {
	
	Category findByName(String name);
	Category findCategoryById(String id);
	
}
