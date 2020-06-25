package com.springboot.amazonclone.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.ManyToOne;
import io.github.kaiso.relmongo.annotation.OneToMany;
import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "cartitem")
public class CartItem {
	

	@Id
	private String id;
	private Product product;
	private int quantity;
	private double  totalprice;
	
	CartItem(){
	}
	
	
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
	

	public String getId() {
		return id;
	}


	@Override
	public String toString() {
		return "CartItem [product=" + product + ", quantity=" + quantity + ", totalprice=" + totalprice + "]";
	}

	
	
	

}
