package com.project.companyprofile.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

/*
 * We are extending LoginVo class because we want to get access to UserVo object
 */
@SuppressWarnings("serial")
public class SocialLoginVo extends LoginVo implements SocialUserDetails{

	public SocialLoginVo(String username, String password, Collection<? extends GrantedAuthority> authorities,
			UserVo userVo) {
		super(username, password, authorities, userVo);
	}

	@Override
	public String getUserId() {
		return getUsername();
	}

}
