package com.springboot.amazonclone.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.service.CustomUserDetailsService;

@Controller
public class LoginController {
	
	@Autowired
	private CustomUserDetailsService userService;

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
	    ModelAndView modelAndView = new ModelAndView();
	    User user = new User();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("signup");
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
	        			System.out.println(userExists);
	        			 modelAndView.setViewName("signup");
	    }
	    
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("signup");
	 
	    } else {
	        userService.saveUser(user);
	        modelAndView.addObject("successMessage", "User has been registered successfully");
	        modelAndView.addObject("user", new User());
	        modelAndView.setViewName("login");

	    }
	    return modelAndView;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByEmail(auth.getName());
	    modelAndView.addObject("currentUser", user);
	    modelAndView.addObject("fullName", "Welcome " + user.getFullname());
	    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	    modelAndView.setViewName("dashboard");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
	public ModelAndView editProfile( ) {
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByEmail(auth.getName());
	    System.out.println(user.getFullname());
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
	
	
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
}
