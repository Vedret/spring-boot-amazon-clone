package com.springboot.amazonclone.entity;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "category")
public class Category {

	    @Id
	    private String id;
	    @NotEmpty(message = "*Please provide a name of category")
	    private String name;

	    public Category() {
	    	
	    }
	    
	
		public Category(String id, String name) {
			super();
			this.id = id;
			this.name = name;
		}


		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		@Override
		public String toString() {
			return "Category [id=" + id + ", name=" + name + "]";
		}
		
		


		
		
	    
	    
}
