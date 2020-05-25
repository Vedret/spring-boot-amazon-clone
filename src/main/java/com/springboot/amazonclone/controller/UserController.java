package com.springboot.amazonclone.controller;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.springboot.amazonclone.entity.Product;
import com.springboot.amazonclone.entity.User;

import com.springboot.amazonclone.repository.ProductRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;


@Controller
public class UserController extends ModelAndAttributeSuperClass {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
	public ModelAndView editProfile( ) {
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByEmail(auth.getName());
	    modelAndView.addObject("currentUser", user);
	    modelAndView.setViewName("edit-profile");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/edit-profile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@ModelAttribute("currentUser")  User editedUser ) {
		ModelAndView modelAndView = new ModelAndView();
	
	        modelAndView.setViewName("edit-profile");
	        //Original user that needs to be updated
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());        
	        userService.updateUser(user,editedUser);        
	        modelAndView.setViewName("login");	
	        return modelAndView;
	        
	}
	
	//Products controller, list all products by category 
	@RequestMapping(value = "/products/{category_id}", method = RequestMethod.GET)
	public ModelAndView allProductsByCategory(@PathVariable String category_id) {

		List<Product> product = productRepository.findByCategoryId(new ObjectId(category_id));		
		System.out.println(product.size());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.setViewName("user/category");
		return modelAndView;
	}
	
	//Products controller, show single product page
	@RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
	public ModelAndView showSingleProduct(@PathVariable String product_id) {

		Product product = productRepository.findProductById(product_id);		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.setViewName("user/product");
		return modelAndView;
	}

}
