package com.ewideplus.companyprofile.admin.service.implementation;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	private UserDetailsService userDetailsService;

	public SocialUserDetailsServiceImpl(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
		return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
	}

	
}
