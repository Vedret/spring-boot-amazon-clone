package com.springboot.amazonclone.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	@Id
    String id;
    String name;
    int age;
 
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
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
    
    
}


