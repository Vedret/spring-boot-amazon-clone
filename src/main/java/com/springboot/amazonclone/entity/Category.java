package com.springboot.amazonclone.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "category")

public class Category {

	    @Id
	    private String id;
	    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
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
