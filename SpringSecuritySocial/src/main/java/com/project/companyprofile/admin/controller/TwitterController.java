package com.project.companyprofile.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterController {
	
	private static final Logger logger = LoggerFactory.getLogger(TwitterController.class); 
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@RequestMapping(value="/admin/twitter/profile")
	public String getProfile(Model model) {
		
		logger.info("Twitter profile");
		
		try {
			Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
			if (connection == null) {
				return "/connect/twitterConnect";
			}
			TwitterProfile profile = connection.getApi().userOperations().getUserProfile();
			model.addAttribute("profile", profile);
			
			return "/connect/twitterProfile";			
		}  catch (NotConnectedException e) {			
			return "/connect/twitterConnect";
		}
	}
	
	@RequestMapping(value="/admin/twitter/post", method=RequestMethod.GET)
	public String composer(Model model) {
		try {
			Connection<Twitter> connection = connectionRepository.findPrimaryConnection(Twitter.class);
		} catch (NotConnectedException e) {
			return "/connect/twitterConnect";
		}
		
		return "post";
	}
	
	@RequestMapping(value="/admin/twitter/post", method=RequestMethod.POST)
	public String post(String message, Model model) {
		try {
			Twitter twitter = connectionRepository.findPrimaryConnection(Twitter.class).getApi();
			model.addAttribute("status", "success");
			model.addAttribute("message", message);
			
			return "posted";
		} catch (Exception e) {
			model.addAttribute("status", "failure");
			
			return "posted";
		}
	}
}