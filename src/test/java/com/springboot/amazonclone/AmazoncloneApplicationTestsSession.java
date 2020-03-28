package com.springboot.amazonclone;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;


import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.UserRepository;


@SpringBootTest
class AmazoncloneApplicationTestsSession {
	
	
	@Autowired
    private UserRepository userMongoRepository;
	

	 

	

}
