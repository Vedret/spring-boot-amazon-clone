package com.springboot.amazonclone.entity;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
	
	@Id
    String id;
	//@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    
	int age;
	
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    
    @NotEmpty(message = "*Please provide your name")
    private String fullName;
    
    private boolean enabled;
    @DBRef
    private Set<Role> roles;
    
    
    public User() {
    }
    
    public User(String name, int age) {
        fullName = name;
        this.age = age;
    }
    

	public User(String name, int age, String email,String password,String role) {
		fullName = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
        		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullName;
    }

    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	@Override
	public String toString() {
		return "User [age=" + age + ", email=" + email + ", password=" + password + ", fullName=" + fullName
				+ ", enabled=" + enabled + ", roles=" + roles + "]";
	}
    
    
    
    
}


