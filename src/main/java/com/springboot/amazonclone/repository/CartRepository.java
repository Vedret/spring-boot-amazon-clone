package com.springboot.amazonclone.repository;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.amazonclone.entity.Cart;

import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
	
	
	
	
	//void update(String id, Cart cart);
	//Now it returns an Optional. Which is not so bad to prevent NullPointerException.
	
	Cart findCartById(String id);
	Cart save(Cart cart);
	
	
	

	//Cart findByCartId(String cartId);
	//Cart saveCart(Cart cart);

	
	


}
