package com.springboot.amazonclone.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.amazonclone.entity.Category;
import com.springboot.amazonclone.entity.Product;
import com.springboot.amazonclone.repository.AlgoliaRepository;
import com.springboot.amazonclone.repository.CategoryRepository;
import com.springboot.amazonclone.repository.ProductRepository;

import io.github.kaiso.relmongo.config.EnableRelMongo;

@Controller
@EnableRelMongo
public class AdminController extends ModelAndAttributeSuperClass {
	
	@Autowired
	AlgoliaRepository algoliaRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(value = "/admin/add-category", method = RequestMethod.GET)
	public ModelAndView category() {
		
		//TEST ALGOLIA INDEX
		/*try {
			algoliaSearchConfig.initAlgoliaIndex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//
	    ModelAndView modelAndView = new ModelAndView();
	    //Category category = new Category();
	    modelAndView.addObject("category", new Category());
	    modelAndView.setViewName("admin/add-category");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/admin/add-category", method = RequestMethod.POST)
	public ModelAndView addCategory(@Valid Category category, BindingResult bindingResult/*,
            RedirectAttributes redirectAttributes */  ) {
           
		
		ModelAndView modelAndView = new ModelAndView();
		
		//Check if there is category with same name as new category that has to be stored
		Category newCategory = categoryRepository.findByName(category.getName());
		//If not, than save it
		 if(newCategory == null) {
	        	
	        	categoryRepository.save(category);
	        	modelAndView.addObject("successMessage", "New category has been registered successfully");
		        //modelAndView.addObject("category", new Category());
	        	modelAndView.setViewName("admin/add-category");
	        	
	        	
	        }else
	        
		 {   
        	
        	//send error message, category exists already       	
        	bindingResult
            .rejectValue("name", "category.name",
                    "There is already a category registered with the category name provided");
        	 
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
	
	
	//Get and Post methods for adding new product
	@RequestMapping(value = "/admin/add-product", method = RequestMethod.GET)
	public ModelAndView product() {
		
		    ModelAndView modelAndView = new ModelAndView();		  
		    //Instantiate Product in place
		    modelAndView.addObject("product", new Product());
		    modelAndView.setViewName("admin/add-product");
		    return modelAndView;
	}
	
	@RequestMapping(value = "/admin/add-product", method = RequestMethod.POST)
	public ModelAndView addNewProduct(@Valid Product addNewProduct, BindingResult bindingResult) {
		    ModelAndView modelAndView = new ModelAndView();
		    
		  if (bindingResult.hasErrors()) {			 
		   modelAndView.setViewName("admin/add-product");
		    }else {
				    try {
				    	
				    	//Save product to Mongo db
						productRepository.save(addNewProduct);
						//Save product to Algolia search
						algoliaRepository.initAlgoliaIndex(addNewProduct);
						modelAndView.addObject("successMessage", "New product has been registered successfully");
				        
					} catch (Exception e) {
						modelAndView.addObject("errorMessage", e);
						e.printStackTrace();
					}
	
				    modelAndView.setViewName("admin/add-product");
				    }
		    return modelAndView;   
	}
}
