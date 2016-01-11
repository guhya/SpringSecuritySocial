package com.ewideplus.companyprofile.admin.service.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ewideplus.companyprofile.admin.service.CommonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonServiceIimpl implements CommonService{
	
	private Logger logger = LoggerFactory.getLogger(CommonServiceIimpl.class);
	
	private Properties prop = new Properties();

	@Override
	public boolean verifyCaptcha(String response, String remoteIp) {
		logger.info(
			"Verifying " + loadProperties("google.recaptcha.secret") 
			+ ", to " + loadProperties("google.recaptcha.verifyUrl")
			+ ", by " + remoteIp
		);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("secret", loadProperties("google.recaptcha.secret"));
		map.add("response", response);
		map.add("remoteip", remoteIp);
		
		RestTemplate restTemplate = new RestTemplate();
		String jsonResponse = restTemplate.postForObject(loadProperties("google.recaptcha.verifyUrl"), map, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>(); 
		try {
			jsonMap = mapper.readValue(jsonResponse, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String success = jsonMap.get("success").toString();		
		
		logger.info("ReCaptcha verification : " + success);
		return "true".equals(success);
		
	}
	
	private String loadProperties(String key){
		String value = "";
		InputStream stream = this.getClass().getResourceAsStream("/properties/app.properties");
		try {
			prop.load(stream);
			value = (String) prop.get(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	

}
