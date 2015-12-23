package com.ewideplus.companyprofile.admin.service;

import java.util.List;

import com.ewideplus.companyprofile.vo.RoleVo;
import com.ewideplus.companyprofile.vo.UserVo;

public interface RoleService {
	
	public int insertRole(RoleVo roleVo);
	List<String> listUserRoles(UserVo userVo);

}
