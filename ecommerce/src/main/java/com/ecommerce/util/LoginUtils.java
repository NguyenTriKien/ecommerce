package com.ecommerce.util;

import java.util.ArrayList;
import java.util.List;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ecommerce.model.UserAccountInfo;

@Component
public class LoginUtils  {
     
	
	 public UserDetails buildUser(UserAccountInfo userAccountInfo) {
		  
		    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		    UserDetails userDetail = new User(userAccountInfo.getUsername(), userAccountInfo.getPassword(), authorities);
		    return userDetail;
	}

}
