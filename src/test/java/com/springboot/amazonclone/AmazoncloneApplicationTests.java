package com.springboot.amazonclone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.UserRepository;


@SpringBootTest
class AmazoncloneApplicationTests {
	
	
	@Autowired
    private UserRepository userMongoRepository;
	
    @BeforeEach
    public void setUp() throws Exception {
        User user1= new User("Alice", 23);
        User user2= new User("Bob", 38);
        //save product, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save
        this.userMongoRepository.save(user1);
        this.userMongoRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        User userA = userMongoRepository.findByFullName("Bob");
        assertNotNull(userA);
        assertEquals(38, userA.getAge());
        /*Get all products, list should only have two*/
        Iterable<User> users = userMongoRepository.findAll();
        int count = 0;
        for(User p : users){
            count++;
        }
        assertEquals(count, 2);
    }
	
    @Test
    public void testDataUpdate(){
        /*Test update*/
        User userB = userMongoRepository.findByFullName("Bob");
        userB.setAge(40);
        userMongoRepository.save(userB);
        User userC= userMongoRepository.findByFullName("Bob");
        assertNotNull(userC);
        assertEquals(40, userC.getAge());
    }
    @AfterEach
    public void tearDown() throws Exception {
      this.userMongoRepository.deleteAll();
    }
	

}
