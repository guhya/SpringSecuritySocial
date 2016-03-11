package com.project.companyprofile.admin.service.implementation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.companyprofile.admin.dao.UserDAO;
import com.project.companyprofile.admin.service.UserService;
import com.project.companyprofile.vo.UserVo;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public int insert(UserVo userVo) {
		
		//Encode password using BCrypt
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(userVo.getPassword());
		logger.info("Bcrypt hashed password : " + hashedPassword);
		userVo.setPassword(hashedPassword);
		
		return userDao.insert(userVo);
	}

	@Override
	public List<Map<String, String>> list(UserVo userVo) {
		return userDao.list(userVo);
	}

	@Override
	public Map<String, String> select(UserVo userVo) {
		return userDao.select(userVo);
	}

}
