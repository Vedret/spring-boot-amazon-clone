package com.springboot.amazonclone.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "cartitem")
public class CartItem {
	
	private CartItem(){
	}
	
	
	private Product product;
	private int quantity=1;
	private double  price=0;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
