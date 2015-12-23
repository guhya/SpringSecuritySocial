package com.ewideplus.companyprofile.admin.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ewideplus.companyprofile.admin.dao.UserDAO;
import com.ewideplus.companyprofile.admin.service.RoleService;
import com.ewideplus.companyprofile.vo.RoleVo;
import com.ewideplus.companyprofile.vo.UserVo;

@Component
public class RoleServiceImpl implements RoleService {

	@Autowired
	private UserDAO userDao;

	@Override
	public int insertRole(RoleVo roleVo) {
		return userDao.insertRole(roleVo);
	}
	
	public List<String> listUserRoles(UserVo userVo) {
		return (List<String>) userDao.listUserRoles(userVo);
	}

}
