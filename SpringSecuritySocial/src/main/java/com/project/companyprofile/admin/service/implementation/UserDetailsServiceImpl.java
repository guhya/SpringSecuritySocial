package com.project.companyprofile.admin.service.implementation;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.companyprofile.admin.controller.UserController;
import com.project.companyprofile.admin.service.LoginService;
import com.project.companyprofile.vo.LoginVo;
import com.project.companyprofile.vo.UserVo;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserVo userVo;

	@Autowired
	private HttpServletRequest request;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String userIp = request.getRemoteAddr();
		logger.info(userIp);
		
		userVo = loginService.buildPrincipal(username);
		
		return new LoginVo(userVo.getUsername(), userVo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(userVo.getRoleString()), userVo);
		
		/*
		If you wish to return custom user object implementing UserDetails, make sure to override hashCode() and equals()
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();		
		for(String role : userRoles){
			authorities.add(new SimpleGrantedAuthority(role));
		}
		userVo.setUsername(result.get("username").toString());
		userVo.setPassword(result.get("password").toString());
		userVo.setEnabled(result.get("enabled").toString());
		userVo.setUserAuthorities(authorities);
		return userVo;
		*/		
	}
}
