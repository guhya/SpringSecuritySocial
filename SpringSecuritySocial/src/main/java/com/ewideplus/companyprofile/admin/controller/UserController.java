package com.ewideplus.companyprofile.admin.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ewideplus.companyprofile.admin.service.UserService;
import com.ewideplus.companyprofile.vo.UserVo;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
	public String list(Model model) 
	{
		logger.info("List");
		return "/admin/user/list";
	}

	@RequestMapping(value = "/admin/user/detail/{name}", method = RequestMethod.GET)
	public String detail(@PathVariable String name, Model model, Principal principal ) 
	{
		logger.info("Detail");
		
		UserVo userVo = new UserVo();
		userVo.setUsername(name);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map result = new HashMap(userService.select(userVo));
		
		model.addAttribute("user", result);
		
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
