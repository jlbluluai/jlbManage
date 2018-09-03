package com.xyz.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.common.CommonElement;
import com.xyz.domain.User;
import com.xyz.dto.StringDto;
import com.xyz.service.UserInfoService;
import com.xyz.service.UserService;

@Controller
public class UserController {

	// 日志
	private static Logger logger = Logger.getLogger(UserManageController.class);

	// 引入的服务
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("userInfoService") 
	private UserInfoService userInfoService;

	@RequestMapping(value = "/getHeadPic", method = RequestMethod.GET)
	@ResponseBody
	public StringDto getHeadPic(HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "获取头像开始" + CommonElement.separator);

		String headpic = (String) session.getAttribute("headpic");
		
		User user = (User)session.getAttribute("user");
		String nickname = user.getNickname();

		String str[] = new String[2];
		str[0] = headpic;
		str[1] = nickname;
		stringDto.setStr(str);

		logger.info(CommonElement.separator + "获取头像结束" + CommonElement.separator);
		return stringDto;
	}

}
