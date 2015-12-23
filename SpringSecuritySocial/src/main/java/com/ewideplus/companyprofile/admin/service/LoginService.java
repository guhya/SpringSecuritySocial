package com.ewideplus.companyprofile.admin.service;

import com.ewideplus.companyprofile.vo.UserVo;

public interface LoginService {
	public UserVo getLoggedInUser();
	public UserVo buildPrincipal(String username);

}
