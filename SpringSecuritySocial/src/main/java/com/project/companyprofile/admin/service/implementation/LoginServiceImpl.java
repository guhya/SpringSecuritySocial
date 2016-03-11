package com.project.companyprofile.admin.service.implementation;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import com.project.companyprofile.admin.service.LoginService;
import com.project.companyprofile.admin.service.RoleService;
import com.project.companyprofile.admin.service.UserService;
import com.project.companyprofile.vo.LoginVo;
import com.project.companyprofile.vo.UserVo;

@Component
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserVo userVo;

	@Override
	public UserVo getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LoginVo loggedInUser = (LoginVo) principal;	
		
		return loggedInUser.getUserVo(); 
	}
	
	@Override
	public UserVo buildPrincipal(String username) {
		userVo.setUsername(username);
		
		Map<String, String> result = userService.select(userVo);
		List<String> userRoles = roleService.listUserRoles(userVo);
		
		String sRoles 		= StringUtils.join(userRoles, ",");
		String password		= result.get("password");
		String firstName	= result.get("firstName");
		String lastName		= result.get("lastName");
		String regDate		= result.get("regDate") != null ? new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(result.get("regDate")) : "";
				
		userVo.setPassword(password);
		userVo.setFirstName(firstName);
		userVo.setLastName(lastName);
		userVo.setRegDate(regDate);
		userVo.setRoleString(sRoles);
		
		return userVo;
		
	}

}
