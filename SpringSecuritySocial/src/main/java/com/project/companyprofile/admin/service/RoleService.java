package com.project.companyprofile.admin.service;

import java.util.List;

import com.project.companyprofile.vo.RoleVo;
import com.project.companyprofile.vo.UserVo;

public interface RoleService {
	
	public int insertRole(RoleVo roleVo);
	List<String> listUserRoles(UserVo userVo);

}
