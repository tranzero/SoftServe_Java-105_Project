package com.ita.edu.softserve.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserManager usersmanager;
	
	/**
	 * Custom UserDetailsService implementation
	 * 
	 * @param username
	 * @return org.springframework.security.core.userdetails.User Object
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {	
		Users user = usersmanager.findByUsername(username);
		if (user.equals(null)) {
	        throw new UsernameNotFoundException("Username " + username + " not found");
	    }        
	    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	    Role role = user.getRole();	        
	    authorities.add(new SimpleGrantedAuthority(role.name()));	       
	    return new User(user.getUserName(), user.getPassword(), true,
	        true, true, true, authorities);		
	}	
}
