package com.springboot.amazonclone.controller;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.amazonclone.entity.Cart;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.CartRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;

@Controller
public class LoginController extends ModelAndAttributeSuperClass {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("accounts/login");
	    return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
	    ModelAndView modelAndView = new ModelAndView();
	    User user = new User();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("accounts/signup");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	    ModelAndView modelAndView = new ModelAndView();
	    User userExists = userService.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	        bindingResult
	                .rejectValue("email", "error.email",
	                        "There is already a user registered with the username provided");
	        			
	        			 modelAndView.setViewName("accounts/signup");
	    }
	    
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("accounts/signup");
	 
	    } else {
	    	userService.saveUser(user);
	    	user = userService.findUserByEmail(user.getEmail());
	    	//Create cart with user Id
	    	 cartRepository.save(new Cart (user.getId()) );
	        modelAndView.addObject("successMessage", "User has been registered successfully");
	        modelAndView.addObject("user", new User());
	        modelAndView.setViewName("accounts/login");

	    }
	    return modelAndView;
	}
	

	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	

	
}
