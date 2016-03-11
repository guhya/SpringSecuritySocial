/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.project.companyprofile.admin.misc;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.thymeleaf.util.StringUtils;

import com.project.companyprofile.admin.service.RoleService;
import com.project.companyprofile.vo.UserVo;

public class SimpleSignInAdapter implements SignInAdapter {
	
	private Logger logger = LoggerFactory.getLogger(SimpleSignInAdapter.class);

	private final RequestCache requestCache;
	
	private RoleService roleService;
	private UserVo userVo;

	@Inject
	public SimpleSignInAdapter(RequestCache requestCache, UserVo userVo, RoleService roleService) {
		this.requestCache 	= requestCache;
		this.userVo			= userVo;
		this.roleService	= roleService;
	}
	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		
		logger.info("Signin adapter fired !");
		/*
		userVo.setUsername(localUserId);
		List<String> userRoles = roleService.listUserRoles(userVo);
		String sRoles = StringUtils.join(userRoles, ",");
		SignInUtils.signin(userVo.getUsername(), AuthorityUtils.commaSeparatedStringToAuthorityList(sRoles));
		 */		
		return extractOriginalUrl(request);
	}

	private String extractOriginalUrl(NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
	}
		 
	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}
