package com.springboot.amazonclone.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.amazonclone.entity.Cart;
import com.springboot.amazonclone.entity.CartItem;
import com.springboot.amazonclone.entity.Product;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.CartRepository;
import com.springboot.amazonclone.repository.ProductRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;

@Controller
public class CartController extends ModelAndAttributeSuperClass  {

	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;

	
	@RequestMapping(value="/cart/add/{productId}",method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void addCartItem(@PathVariable (value="productId")String productId, HttpServletRequest request) {

		// Get userid from security context, we need UID to get cart ID
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		//If there is no cart with id same as user id create one
		//cartRepository.save(new Cart (user.getId()) );
		if((cartRepository.findCartById(user.getId())==null)){
			cartRepository.save(new Cart (user.getId()) );
			
		}
		
		Cart cart = cartRepository.findCartById(user.getId());
		//Check if there is a product in db
		if (productRepository.findProductById(productId) != null) {
			Product product = productRepository.findProductById(productId);
			//System.out.println("==========+++"+product);
			cart.addCartItem(new CartItem(product));
			// On save Mongo will update if exists or create new if not exists
			cartRepository.save(cart);

		}else {
			throw new IllegalArgumentException(new Exception());
		}
		
	
}	
	
	
	@RequestMapping(value="/cart/remove/{productId}",method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void removeCartItem(@PathVariable (value="productId")String productId, HttpServletRequest request) {

		// Get userid from security context, we need UID to get cart ID
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Cart cart = cartRepository.findCartById(user.getId());		
		if (productRepository.findProductById(productId) != null) {
			
			Product product = productRepository.findProductById(productId);
			cart.removeCartItem(new CartItem(product));
			cart = cartRepository.save(cart);
			
		}else{
			
			throw new IllegalArgumentException(new Exception());
		}
		
	
}
	
	//Delete cart
	@RequestMapping(value="/cart/deleteCart",method=RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteCart() {
		
		System.out.println("Hello From delete cart");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		Cart cart = cartRepository.findCartById(user.getId());
		cartRepository.delete(cart);
	}
	

	//Get cart to the cartAngular
	@RequestMapping(value="/rest/cart/{cartId}",method=RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value="cartId")String cartId){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return cartRepository.findCartById(user.getId());
	}
	
	
	//Cart page
	@RequestMapping(value="/cart/{cartId}",method=RequestMethod.GET)
	public ModelAndView getCart(@PathVariable (value="cartId") String cartId){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("cartId",cartId);
		modelAndView.setViewName("user/cart");
		return modelAndView;
		
	}

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload.")
    public void handleClientErrors (Exception e) {}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error.")
    public void handleServerErrors (Exception e) {}
}

	

	

