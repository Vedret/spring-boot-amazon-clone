package com.springboot.amazonclone;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.amazonclone.entity.Role;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.RoleRepository;
import com.springboot.amazonclone.repository.UserRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;


@SpringBootTest
class AmazoncloneApplicationUserServiceTest {
	
	@Autowired
    private UserRepository userMongoRepository;
	
	@Autowired
	 private RoleRepository roleMongoRepository;
	@Autowired
	 private CustomUserDetailsService userServiceUnderTest;
	 
    @BeforeEach
    public void setUp() throws Exception {
    	
    	
        User user= new User("Alice", 23,"bhdk@boki","password","ADMIN");
        
        Role newAdminRole = new Role();
        newAdminRole.setRole("ADMIN");
     
        roleMongoRepository.save(newAdminRole);
        userServiceUnderTest.saveUser(user);
    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        User userA = userMongoRepository.findByFullName("Alice");
        assertEquals(23, userA.getAge());
        
        Role adminRole = roleMongoRepository.findByRole("ADMIN");
        assertEquals("ADMIN", adminRole.getRole());
       
    }
    
    @AfterEach
    public void tearDown() throws Exception {
      this.userMongoRepository.deleteAll();
      this.roleMongoRepository.deleteAll();
    }
    
}
