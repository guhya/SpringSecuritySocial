package com.ewideplus.companyprofile.admin.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.ewideplus.companyprofile.admin.service.LoginService;
import com.ewideplus.companyprofile.vo.SocialLoginVo;
import com.ewideplus.companyprofile.vo.UserVo;

/*
 * This may seem redundant, need a service that populate login object with user information
 * and also role information
 */
public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserVo userVo;

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		userVo.setUsername(userId);
		
		userVo = loginService.buildPrincipal(userId);
		
		return new SocialLoginVo(userVo.getUsername(), userVo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(userVo.getRoleString()), userVo);
	}

	
}
