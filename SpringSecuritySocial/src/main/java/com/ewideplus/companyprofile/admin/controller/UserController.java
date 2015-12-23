package com.ewideplus.companyprofile.admin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ewideplus.companyprofile.admin.service.LoginService;
import com.ewideplus.companyprofile.admin.service.UserService;
import com.ewideplus.companyprofile.vo.UserVo;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserVo userVo;
	
	@RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
	public String list(Model model) 
	{
		logger.info("List");
		
		List<Map<String, String>> userList = userService.list(userVo);
		model.addAttribute("userList", userList);
		
		return "/admin/user/list";
	}

	@RequestMapping(value = "/admin/user/detail/{name}", method = RequestMethod.GET)
	public String detail(@PathVariable String name, Model model, UserVo userVo) 
	{
		logger.info("Detail");
		
		userVo.setUsername(name);
		Map<String, String> result = userService.select(userVo);
		
		model.addAttribute("user", result);
		
		return "/admin/user/detail";
	}
	
	@RequestMapping(value = "/admin/user/mydetail", method = RequestMethod.GET)
	public String myDetail(Model model, UserVo userVo) 
	{
		logger.info("My Detail");
		
		userVo = loginService.getLoggedInUser();
		model.addAttribute("user", userVo);
		
		return "/admin/user/detail";
	}

	@RequestMapping(value = "/admin/user/insert/{name}", method = RequestMethod.GET)
	public String insert(@PathVariable String name, Model model) 
	{
		logger.info("{} Welcome", name);
		
		UserVo userVo = new UserVo();
		userVo.setUsername(name);
		userVo.setPassword("password");
		userVo.setFirstName("first");
		userVo.setLastName("last");
		userVo.setEnabled("Y");
		userVo.setDelYn("N");
		
		System.out.println(userService.insert(userVo));
		model.addAttribute("name", name);
		
		return "/admin/user/form";
	}

	
}
