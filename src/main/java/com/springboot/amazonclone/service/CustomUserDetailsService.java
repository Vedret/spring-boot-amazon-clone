package com.springboot.amazonclone.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.springboot.amazonclone.entity.Role;
import com.springboot.amazonclone.entity.User;
import com.springboot.amazonclone.repository.RoleRepository;
import com.springboot.amazonclone.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	//Create a method for getting the user by email.
	public User findUserByEmail(String email) {
	    return userRepository.findByEmail(email);
	}
	
	//Create a method for save a new user, encrypt the password and set a role for the user. For now, we will use the role `ADMIN` for all newly registered user.
	 public void saveUser(User user) {
		System.out.println("------>>>" + user);
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    Role userRole = roleRepository.findByRole("ADMIN");
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	 }
	 
	 public void updateUser(User user ,User editedUser ) {
		 
		   User updateUser = userRepository.findByEmail(user.getEmail());
		   updateUser.setPassword(bCryptPasswordEncoder.encode(editedUser.getPassword()));
		   updateUser.setEnabled(true);
		   updateUser.setAge(55);
		   updateUser.setEmail(editedUser.getEmail());
		   updateUser.setFullname(editedUser.getFullname());
		   
		    Role userRole = roleRepository.findByRole("ADMIN");
		    updateUser.setRoles(new HashSet<>(Arrays.asList(userRole)));
		    userRepository.save(updateUser);
		 
	 }

	 //Create a method for handling the login mechanism that checks or compares username with the user from MongoDB collection.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	//method for converting the user roles as GrantedAuthority collection. Create a new method like this.

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	//method for connecting MongoDB user to Spring Security user as called from the `loadUserByUsername` method.
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}
