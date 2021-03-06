package com.springboot.amazonclone.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.amazonclone.entity.Product;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.ProductRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;

import io.github.kaiso.relmongo.config.EnableRelMongo;


@Controller
@EnableRelMongo
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
	    modelAndView.setViewName("user/edit-profile");
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

		List<Product> product = productRepository.findByCategoryId(category_id);		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.setViewName("user/category");
		return modelAndView;
	}
	
	//Products controller, show single product page/ 
	@RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
	public ModelAndView showSingleProduct(@PathVariable String product_id) {
		
		
		Product product = productRepository.findProductById(product_id);	
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("product", product);
		modelAndView.setViewName("user/product");
		return modelAndView;
	}
	

	
	
	
	
	//POST parameter q to a search
	@RequestMapping(value = "/product/search", method = RequestMethod.POST)
	public String searchProduct(@RequestBody String q){
		
		return "redirect:/product/search?" + q;
	}
	
	//Get parameter q from GET method with @Requestparam and do a query with it
		@RequestMapping(value = "/product/search", method = RequestMethod.GET)
		public ModelAndView search(@RequestParam  String q) {
			ModelAndView modelAndView = new ModelAndView();
			System.out.println("------------>>>>"+q);
			try {
				List<Product> product = productRepository.findProductsWithNameLike(q);
				System.out.println("------------>>>>"+product);
				modelAndView.addObject("search", product);
			} catch (Exception e) {
				// here you should handle unexpected errors
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			modelAndView.setViewName("user/search-result");
			return modelAndView;
		}
		
		//Products controller, show 10 product per page to dashboard
		@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
		public ModelAndView dashboard(@PageableDefault(size = 9) Pageable pageable) {
		    ModelAndView modelAndView = new ModelAndView();
		    //List<Product> product = productRepository.findAll();
		    //Send all product to the dashboard
		    Page<Product> page = productRepository.findAll(pageable);
		  
		    modelAndView.addObject("page", page );
		    modelAndView.setViewName("dashboard-page");
		    return modelAndView;
		}
		
}
