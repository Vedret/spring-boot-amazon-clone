package com.springboot.amazonclone.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springboot.amazonclone.entity.Cart;

@Service
public class CartService {
	
private Map<String ,Cart> listOfCarts;
	
	public  void CartImpl(){
		listOfCarts = new HashMap<String,Cart>();
	}
	
	public Cart create (Cart cart){
		if(listOfCarts.keySet().contains(cart.getCartId())){
			throw new IllegalArgumentException(String.format("Cannot create a cart. A cart with given id(%)" +
					"already" +
					"exists",cart.getCartId()));
		}
		listOfCarts.put(cart.getCartId(),cart);
		return cart;
	}

}
