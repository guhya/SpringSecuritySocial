package com.project.companyprofile.admin.service;

import com.project.companyprofile.vo.UserVo;

public interface LoginService {
	public UserVo getLoggedInUser();
	public UserVo buildPrincipal(String username);

}
