package com.ewideplus.companyprofile.admin.service.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ewideplus.companyprofile.admin.dao.UserDAO;
import com.ewideplus.companyprofile.admin.service.UserService;
import com.ewideplus.companyprofile.vo.UserVo;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;
	
	//private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public int insert(UserVo userVo) {
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
