package com.springboot.amazonclone.entity;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.ManyToOne;
import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "product")
public class Product {
	
	@Id
    String  id;
	
	@NotEmpty(message = "*Please provide a name of product")
	private String name;
	
	@Range(min=0,max=1000000)//(message = "*Please provide a price of product")
	private Number price;
	
	private String image;
	
	 //Product will have only one Category 
	//LAZY = fetch when needed. In our case fetch products only when we need it
    //EAGER = fetch immediately, will always fetch the products. Loads all the relationship
	 //@NotEmpty(message = "*Please provide a category, or create new one")
	//No cascade, we want to leave category intact while saving product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinProperty(name="id")
	private Category category;
	
    public Product() {
    	
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Number getPrice() {
		return price;
	}
	public void setPrice(Number price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
 	
}
