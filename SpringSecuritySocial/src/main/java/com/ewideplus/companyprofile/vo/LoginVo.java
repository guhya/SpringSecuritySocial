package com.ewideplus.companyprofile.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class LoginVo extends User {
	
	private UserVo userVo;

	public LoginVo(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}	
	

}
