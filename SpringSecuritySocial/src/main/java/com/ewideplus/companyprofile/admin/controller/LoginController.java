package com.ewideplus.companyprofile.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.util.StringUtils;

import com.ewideplus.companyprofile.admin.misc.SignInUtils;
import com.ewideplus.companyprofile.admin.service.CommonService;
import com.ewideplus.companyprofile.admin.service.LoginService;
import com.ewideplus.companyprofile.admin.service.RoleService;
import com.ewideplus.companyprofile.admin.service.UserService;
import com.ewideplus.companyprofile.vo.LoginVo;
import com.ewideplus.companyprofile.vo.RoleVo;
import com.ewideplus.companyprofile.vo.UserVo;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private LoginService loginService;	
	
	@Autowired
	private CommonService commonService;	

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
	public String formProcessor(Model model, WebRequest request, HttpServletRequest httpRequest, @ModelAttribute("userVo") UserVo userVo) 
	{
		logger.info("Form Processor");
		
		String recaptcha = httpRequest.getParameter("g-recaptcha-response");		
		boolean captchaPassed = commonService.verifyCaptcha(recaptcha, httpRequest.getRemoteAddr());
		
		if(!captchaPassed){
			model.addAttribute("user", userVo);
			return "/admin/login/register";
		}
		
		//Create new user
		int success = userService.insert(userVo);
		
		Connection<?> connection 	= providerSignInUtils.getConnectionFromSession(request);
		String providerId 			= "";
		List<String> roles			= new ArrayList<String>();
		
		//If user is associated with the provider (Social User), prepare role 
		if(connection != null){
			providerId 	= connection.getKey().getProviderId();
			roles.add("ROLE_USER");
		}else{
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_USER");
		}
		
		//Save role for the new user
		for(String role : roles){
			RoleVo roleVo = new RoleVo(userVo.getUsername(), role);
			success		= roleService.insertRole(roleVo);
		}
		
		if(success == 1){
			
			//If registered through facebook/twitter
			if(!"".equals(providerId)){
				
				//Get user role from database
				List<String> userRoles = roleService.listUserRoles(userVo);
				String sRoles = StringUtils.join(userRoles, ",");
				
				//Try to sign in using the newly created account
				userVo = loginService.buildPrincipal(userVo.getUsername());
				SignInUtils.signin(new LoginVo(userVo.getUsername(), userVo.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(sRoles), userVo), AuthorityUtils.commaSeparatedStringToAuthorityList(sRoles));
				providerSignInUtils.doPostSignUp(userVo.getUsername(), request);
			
				return "redirect:/admin/"+providerId+"/profile";
			}else{
				//Normal registration
				return "redirect:/admin/user/mydetail";
			}
		}
		
		model.addAttribute("user", userVo);
		return "/admin/login/register";
	}	
	
}
