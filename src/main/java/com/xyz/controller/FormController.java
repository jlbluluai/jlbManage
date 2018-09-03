package com.xyz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyz.util.Utils;

@Controller
public class FormController {

	private static final String[] blog = { "blogApply", "blogSta", "classifyManage" };
	private static final String[] frontMaintain = { "pubboard", "recommendManage", "versionManage" };
	private static final String[] login = { "login" };
	private static final String[] message = { "pubMessage" };
	private static final String[] oneself = { "headpicChange", "personal" };
	private static final String[] opinion = { "opinionHandle" };
	private static final String[] user = { "bloggerApply", "bloggerSta", "passRecord", "users" };
	private static final String[] portal = { "index", "main" };

	/**
	 * 根据路径找到应该展示的视图位置并展示视图
	 * 
	 * @author 叶灬黎
	 * @param formname
	 * @return
	 */
	@RequestMapping(value = "/{formname}")
	public String loginForm(@PathVariable String formname) {
		String path = null;
		if (Utils.isThisStringGroup(blog, formname)) {
			path = "views/blog/" + formname;
		} else if (Utils.isThisStringGroup(frontMaintain, formname)) {
			path = "views/frontMaintain/" + formname;
		} else if (Utils.isThisStringGroup(login, formname)) {
			path = "views/login/" + formname;
		} else if (Utils.isThisStringGroup(message, formname)) {
			path = "views/message/" + formname;
		} else if (Utils.isThisStringGroup(oneself, formname)) {
			path = "views/oneself/" + formname;
		} else if (Utils.isThisStringGroup(opinion, formname)) {
			path = "views/opinion/" + formname;
		} else if (Utils.isThisStringGroup(user, formname)) {
			path = "views/user/" + formname;
		} else if (Utils.isThisStringGroup(portal, formname)) {
			path = formname;
		} else {
			path = "error";
		}
		return path;
	}

}