package com.springboot.amazonclone.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.amazonclone.entity.Category;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.CategoryRepository;

@Controller
public class AdminController extends ModelAndAttributeSuperClass {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/admin/add-category", method = RequestMethod.GET)
	public ModelAndView category() {
	    ModelAndView modelAndView = new ModelAndView();
	    Category category = new Category();
	    modelAndView.addObject("category", category);
	    modelAndView.setViewName("admin/add-category");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/admin/add-category", method = RequestMethod.POST)
	public ModelAndView addCategory(@Valid Category category, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Category newCategory = categoryRepository.findByName(category.getName());
		
		 if(newCategory == null) {
	        	
	        	categoryRepository.save(category);
	        	modelAndView.addObject("successMessage", "New category has been registered successfully");
		        modelAndView.addObject("category", new Category());
	        	modelAndView.setViewName("admin/add-category");
	        	
	        	
	        }else
	        
		 {   
        	
        	//send error message category exists already
        	System.out.println("ELSEEEEE");
        	bindingResult
            .rejectValue("category", "category.email",
                    "There is already a category registered with the category provided");
        	 
    		 modelAndView.setViewName("admin/add-category");      	
        	
        }
       /* if (bindingResult.hasErrors()) {
        	bindingResult
            .rejectValue("category", "category.email",
                    "Error");
	        modelAndView.setViewName("admin/add-category");
        }*/
       
	    
	    
		return modelAndView;
		
	}

}
