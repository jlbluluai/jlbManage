package com.xyz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.domain.User;
import com.xyz.util.Utils;

@Controller
public class InterceptorController {
	
	private static final String[] blog = { "blogApply", "blogSta", "classifyManage" };
	private static final String[] frontMaintain = { "pubboard", "recommendManage", "versionManage" };
	private static final String[] login = { "login" };
	private static final String[] message = { "pubMessage" };
	private static final String[] oneself = { "headpicChange", "personal" };
	private static final String[] opinion = { "opinionHandle" };
	private static final String[] _user = { "bloggerApply", "bloggerSta", "passRecord", "users" };
	private static final String[] portal = { "index", "main" };


	@RequestMapping(value = "/loginInterceptor/{pathh}")
	public String loginInterceptor(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String pathh) throws Exception {


		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "error";
		}
		
		String path = null;
		if (Utils.isThisStringGroup(blog, pathh)) {
			path = "views/blog/" + pathh;
		} else if (Utils.isThisStringGroup(frontMaintain, pathh)) {
			path = "views/frontMaintain/" + pathh;
		} else if (Utils.isThisStringGroup(login, pathh)) {
			path = "views/login/" + pathh;
		} else if (Utils.isThisStringGroup(message, pathh)) {
			path = "views/message/" + pathh;
		} else if (Utils.isThisStringGroup(oneself, pathh)) {
			path = "views/oneself/" + pathh;
		} else if (Utils.isThisStringGroup(opinion, pathh)) {
			path = "views/opinion/" + pathh;
		} else if (Utils.isThisStringGroup(_user, pathh)) {
			path = "views/user/" + pathh;
		} else if (Utils.isThisStringGroup(portal, pathh)) {
			path = pathh;
		} else {
			path = "error";
		}

		return path;
	}

}
