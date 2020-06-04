package com.springboot.amazonclone.entity;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "cart")
public class Cart {
	
	private User owner;
	private Map<String , CartItem> cartItems;
	private double grandTotal;
	
	private Cart(){
	}
	
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	

}
