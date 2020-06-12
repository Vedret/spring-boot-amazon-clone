package com.springboot.amazonclone.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "cartitem")
public class CartItem {
	
	CartItem(){
	}
	
	
	private Product product;
	private int quantity;
	private double  totalprice;
	
public CartItem(Product product) {
		
		this.product = product;
		this.quantity = 1;
		this.totalprice = product.getPrice();
	}
	
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
	public double geTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	@Override
	public String toString() {
		return "CartItem [product=" + product + ", quantity=" + quantity + ", totalprice=" + totalprice + "]";
	}

	
	
	

}
