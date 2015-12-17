package com.ewideplus.companyprofile.admin.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.thymeleaf.util.StringUtils;

import com.ewideplus.companyprofile.admin.dao.UserDAO;
import com.ewideplus.companyprofile.admin.service.UserService;
import com.ewideplus.companyprofile.vo.LoginVo;
import com.ewideplus.companyprofile.vo.RoleVo;
import com.ewideplus.companyprofile.vo.UserVo;

public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserVo userVo;
	
	@Autowired
	private HttpServletRequest request;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public int insert(UserVo userVo) {
		return userDao.insert(userVo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVo> list(UserVo userVo) {
		return (List<UserVo>) userDao.select(userVo);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map select(UserVo userVo) {
		return userDao.select(userVo);
	}
	
	@Override
	public int insertRole(RoleVo roleVo) {
		return userDao.insertRole(roleVo);
	}
	
	public List<String> listUserRoles(UserVo userVo) {
		return (List<String>) userDao.listUserRoles(userVo);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		userVo.setUsername(username);
		
		String userIp = request.getRemoteAddr();
		logger.info(userIp);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Map result = new HashMap(userDao.select(userVo));
		
		List<String> userRoles = userDao.listUserRoles(userVo);
		String sRoles = StringUtils.join(userRoles, ",");
		
		/*
		 * If you wish to return custom user object implementing UserDetails, make sure to override hashCode() and equals()
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
		
		return new LoginVo(
							result.get("username").toString(), 
							result.get("password").toString(),							
							AuthorityUtils.commaSeparatedStringToAuthorityList(sRoles)
							);
	}

}
