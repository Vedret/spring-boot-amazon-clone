package com.springboot.amazonclone.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.springboot.amazonclone.entity.Cart;
import com.springboot.amazonclone.entity.CartItem;
import com.springboot.amazonclone.entity.Product;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.CartRepository;
import com.springboot.amazonclone.repository.ProductRepository;
import com.springboot.amazonclone.service.CustomUserDetailsService;

@Controller
public class CartController {

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
		Cart cart = cartRepository.findCartById(user.getId());
		if (productRepository.findProductById(productId) != null) {
			Product product = productRepository.findProductById(productId);
			cart.addCartItem(new CartItem(product));
			// On save Mongo will update if exists or create new if not exists
			cart = cartRepository.save(cart);
			System.out.println("Cart-->" + cart);
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
			System.out.println("Cart-->" + cart);
		}else{
			throw new IllegalArgumentException(new Exception());
		}
}
}		

	

