package com.ewideplus.companyprofile.admin.service;

import java.util.List;
import java.util.Map;

import com.ewideplus.companyprofile.vo.RoleVo;
import com.ewideplus.companyprofile.vo.UserVo;

public interface UserService {	
	public int insert(UserVo userVo);
	public List<UserVo> list(UserVo userVo);
	@SuppressWarnings("rawtypes")
	public Map select(UserVo userVo);
	public int insertRole(RoleVo roleVo);
	List<String> listUserRoles(UserVo userVo);
}
