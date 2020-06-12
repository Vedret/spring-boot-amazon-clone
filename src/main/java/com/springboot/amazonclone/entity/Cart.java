package com.springboot.amazonclone.entity;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "cart")
public class Cart {
	
	@Id
	private String id;
	
	//HashMap can put, remove, delete etc.
	private Map<String , CartItem> cartItems;
	//Total cost of Cart
	private double grandTotal;
	
	//Default cart constructor
	public Cart(){
		cartItems = new HashMap<String , CartItem>();
		grandTotal=0;
	}


	public Cart(String id) {
		super();
		this.id = id;
	}


	public Cart(String id, Map<String, CartItem> cartItems, double grandTotal) {
		super();
		this.id = id;
		this.cartItems = cartItems;
		this.grandTotal = grandTotal;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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

	
	//Add cart item(product), check if it exists, if so update quantity and price of existing product
public void addCartItem (CartItem item){
		
		String productId = item.getProduct().getId();
		
		//The key here is only to update price and quantity to and existing cart. If there is product in our cart update only totalprice and quantity 
		if(cartItems.containsKey(productId)){
			//Instantiate new obj and fill it with our original bject
			CartItem existingCartItem = cartItems.get(productId);
			//increase quantity for 1
			existingCartItem.setQuantity(existingCartItem.getQuantity()+item.getQuantity());
			//generate price
			existingCartItem.setTotalprice(existingCartItem.getQuantity() *existingCartItem.getProduct().getPrice());
			//cartItems.put(productId, existingCartItem);
			System.out.println("cartItems if-->"+ cartItems);
	}else {
		//If not create new product with quantity one and price
		cartItems.put(productId,item);
		System.out.println("cartItems else-->"+ cartItems);
		
	
		//Update grand total
		}updateGrandTotal();
}

//Remove cart item is same as add cart item but reverse
public void removeCartItem (CartItem item){
	
		if ((item.getProduct().getId()).isEmpty()) {
			System.out.println("Emptyyyy ID");
		}

		String productId = item.getProduct().getId();

		if ((cartItems.get(productId)) != null) {
			CartItem existingCartItem = cartItems.get(productId);
			
			if (existingCartItem.getQuantity() > 1) {
				existingCartItem.setQuantity(existingCartItem.getQuantity() - item.getQuantity());
				existingCartItem
						.setTotalprice(existingCartItem.getQuantity() * existingCartItem.getProduct().getPrice());
			} else {
				cartItems.remove(productId);
			}
		}
		updateGrandTotal();
	}

//Update grandTotal of the Cart
	public void updateGrandTotal(){
		grandTotal=0;
		for(CartItem item : cartItems.values()){
			grandTotal=grandTotal + item.geTotalprice();
			System.out.println("GTOTALE==="+ grandTotal);
		}
	}


@Override
public String toString() {
	return "Cart [id=" + id + ", cartItems=" + cartItems + ", grandTotal=" + grandTotal + "]";
}


}
	


