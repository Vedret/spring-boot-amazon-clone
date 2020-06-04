package com.springboot.amazonclone.entity;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.github.kaiso.relmongo.config.EnableRelMongo;

@EnableRelMongo
@Document(collection = "category")
public class Category {

	    @Id
	    private String id;
	    @NotEmpty(message = "*Please provide a name of category")
	    private String name;

	    /*@OneToMany(fetch=FetchType.LAZY)
		@JoinProperty(name="id")	
		private List<Product> product;*/
	    

	    
	    public Category() {
	    	
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
