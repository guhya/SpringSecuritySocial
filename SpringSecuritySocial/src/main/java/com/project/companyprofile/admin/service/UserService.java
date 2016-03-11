package com.project.companyprofile.admin.service;

import java.util.List;
import java.util.Map;

import com.project.companyprofile.vo.UserVo;

public interface UserService {	
	public int insert(UserVo userVo);
	public List<Map<String, String>> list(UserVo userVo);
	public Map<String, String> select(UserVo userVo);
	
}
