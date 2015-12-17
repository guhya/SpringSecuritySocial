package com.ewideplus.companyprofile.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.util.StringUtils;

import com.ewideplus.companyprofile.admin.misc.SignInUtils;
import com.ewideplus.companyprofile.admin.service.UserService;
import com.ewideplus.companyprofile.vo.RoleVo;
import com.ewideplus.companyprofile.vo.UserVo;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	private Map<String, String> user = new HashMap<String, String>();
	private ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

	@RequestMapping(value = "/admin/login")
	public String login(Model model, @RequestParam(value = "error", required = false) String error) 
	{
		logger.info("Login");
		
		if("1".equals(error)){
			model.addAttribute("error", true);
		}
		
		return "/admin/login/login";
	}
	
	@RequestMapping(value = "/admin/register", method=RequestMethod.GET)
	public String form(Model model, WebRequest request) 
	{
		logger.info("Form");
	
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		if (connection != null) {
			
			UserProfile userProfile = connection.fetchUserProfile();
			String username = userProfile.getEmail() != null ? userProfile.getEmail() : userProfile.getUsername();
			
			user.put("firstName", userProfile.getFirstName());
			user.put("lastName", userProfile.getLastName());
			user.put("username", username);
			
			model.addAttribute("message", "Your " + connection.getKey().getProviderId() + " account is not associated with eWIDEPLUS account. If you're new, please sign up.");
			model.addAttribute("user", user);
		}
		
		return "/admin/login/register";
	}
	
	@RequestMapping(value = "/admin/register", method=RequestMethod.POST)
	public String formProcessor(Model model, WebRequest request, @ModelAttribute("userVo") UserVo userVo) 
	{
		logger.info("Form Processor");
		
		//Create new user
		int success = userService.insert(userVo);
		
		Connection<?> connection 	= providerSignInUtils.getConnectionFromSession(request);
		String providerId 			= "";
		String role					= "";
		
		//If user is associated with the provider (Social User), prepare role
		if(connection != null){
			providerId 	= connection.getKey().getProviderId();
			role		= "ROLE_USER";
		}
		
		//Save role for the new user
		RoleVo roleVo = new RoleVo(userVo.getUsername(), role);
		success		= userService.insertRole(roleVo);
		
		if(success == 1 && !"".equals(providerId)){
			
			//Get role from database
			List<String> userRoles = userService.listUserRoles(userVo);
			String sRoles = StringUtils.join(userRoles, ",");
			
			//Try to sign in using the newly created account
			SignInUtils.signin(userVo.getUsername(), AuthorityUtils.commaSeparatedStringToAuthorityList(sRoles));
			providerSignInUtils.doPostSignUp(userVo.getUsername(), request);
			
			return "redirect:/admin/"+providerId+"/profile";
		}
		
		model.addAttribute("user", userVo);
		return "/admin/login/register";
	}	
	
}
