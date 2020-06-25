package com.springboot.amazonclone.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.ManyToOne;
import io.github.kaiso.relmongo.annotation.OneToMany;
import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "cart")
public class Cart {
	

	
	@Id
	private String id;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinProperty(name="cart")
	private List<CartItem> cartItems;
	//Total cost of Cart
	private double grandTotal;

	private  Cart() {
		
		cartItems = new ArrayList<CartItem>();
		this.grandTotal = 0;
	}



	public Cart(String id) {
		super();
		this.id = id;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	


	public List<CartItem> getCartItems() {
		return cartItems;
	}



	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}



	public double getGrandTotal() {
		return grandTotal;
	}



	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	

	//Add cart item(product), 
	public void addCartItem (CartItem item){
		
		String productId = item.getProduct().getId();
		//Looping Method 1: Using for loop: check if it exists, if so update quantity and price of existing product
		for(int i = 0 ; i < cartItems.size() ; i++){
			
			if(cartItems.get(i).getProduct().getId().equals(productId)) {
				cartItems.get(i).setQuantity(cartItems.get(i).getQuantity()+1);
				cartItems.get(i).setTotalprice(cartItems.get(i).getQuantity() * item.getProduct().getPrice());
				System.out.println("CART ITEMS ID=  "+cartItems.size());
				updateGrandTotal();
				return;
			}
			}
		
		cartItems.add(item);
		updateGrandTotal();
	
	}

	//Add cart item(product), 
		public void removeCartItem (CartItem item){
			
			String productId = item.getProduct().getId();
			//Looping Method 1: Using for loop: check if it exists, if so update quantity and price of existing product
			for(int i = 0 ; i < cartItems.size() ; i++){
				
				if(cartItems.get(i).getProduct().getId().equals(productId)) {
					if(cartItems.get(i).getQuantity()!=1) {
					cartItems.get(i).setQuantity(cartItems.get(i).getQuantity()-1);
					cartItems.get(i).setTotalprice(cartItems.get(i).getQuantity() * item.getProduct().getPrice());	
					updateGrandTotal();
					return;
				}else {
					//System.out.println("Cartrepository_CartItem "+cartItems.get(i).getQuantity()+" "+cartItems.get(i).getId());
					cartItems.remove(cartItems.get(i));					
					updateGrandTotal();
				    return;
				}
				}
				}
			
			//cartItems.remove(item);
			//updateGrandTotal();
		
		}
	
	
	
	//Update grandTotal of the Cart
		public void updateGrandTotal(){
			grandTotal=0;
			//Looping Method 2: Using for each loop :
			for(CartItem item : cartItems){
				grandTotal=grandTotal + item.geTotalprice();
				
			}
		}


}
