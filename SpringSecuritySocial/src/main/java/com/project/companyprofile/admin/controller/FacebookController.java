package com.project.companyprofile.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookController {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value="/admin/facebook/profile")
	public String getProfile(Model model) {
		try {
			Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
			if (connection == null) {
				return "/connect/facebookConnect";
			}
			User profile = connection.getApi().userOperations().getUserProfile();
			model.addAttribute("profile", profile);
			
			return "/connect/facebookProfile";			
		}  catch (NotConnectedException e) {			
			return "/connect/facebookConnect";
		}
	}
	
	@RequestMapping(value="/admin/facebook/post", method=RequestMethod.GET)
	public String composer(Model model) {
		try {
			Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		} catch (NotConnectedException e) {
			return "/connect/facebookConnect";
		}
		
		return "post";
	}
	
	@RequestMapping(value="/admin/facebook/post", method=RequestMethod.POST)
	public String post(String message, Model model) {
		try {
			Facebook facebook = connectionRepository.findPrimaryConnection(Facebook.class).getApi();
			facebook.feedOperations().updateStatus(message);
			model.addAttribute("status", "success");
			model.addAttribute("message", message);
			
			return "posted";
		} catch (Exception e) {
			model.addAttribute("status", "failure");
			
			return "posted";
		}
	}
}