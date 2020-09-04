package com.springboot.amazonclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.springboot.amazonclone.entity.Category;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.CategoryRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;

@Controller
// We can user superclass or @ControllerAdvice(annotations = RestController.class) , if we need same model attributes for multiple controllers
public class ModelAndAttributeSuperClass {
	
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	//The category is used in navbar dropdown menu 
	
	@ModelAttribute("categories")
	public Iterable<Category> getCategories(){
		
	    return categoryRepository.findAll();
	    
	}
	
	@ModelAttribute("userOnNavbar")
	public User getUseronNavbar() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User userOnNavBar = userService.findUserByEmail(auth.getName());
		return userOnNavBar;
	}
	
	

}
