package com.xyz.controller;

import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.common.CommonElement;
import com.xyz.domain.User;
import com.xyz.domain.UserInfo;
import com.xyz.dto.StringDto;
import com.xyz.service.UserInfoService;
import com.xyz.service.UserService;
import com.xyz.util.FtpConnect;
import com.xyz.util.Utils;

@Controller
public class LoginController {

	// 日志
	private static Logger logger = Logger.getLogger(LoginController.class);

	// 引入的服务
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;

	/* 登录 */
	@RequestMapping(value = "/goLogin", method = RequestMethod.POST)
	@ResponseBody
	public StringDto goLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "登录验证开始" + CommonElement.separator);

		User item = new User();
		item.setUsername(username);
		item.setPassword(password);

		String message = "";

		User user = userService.getAppointedItem2(item);
		UserInfo userInfo = userInfoService.getAppointedItem1(user.getId());

		String prop = FtpConnect.class.getClassLoader().getResource("/").getPath()
				+ "properties/ftp-connect.properties";
		prop = URLDecoder.decode(prop);
		Properties properties = Utils.getProperties(prop);

		String headpic = "http://" + properties.getProperty("url") + ":" + properties.getProperty("nginxPort") + "/"
				+ userInfo.getHeadpic();

		if (user != null) {
			session.setAttribute("user", user);
			session.setAttribute("headpic", headpic);
			message = "yes";
		} else {
			message = "请核实用户名或密码！！！";
		}

		stringDto.setMessage(message);

		logger.info(CommonElement.separator + "登录验证结束" + CommonElement.separator);
		return stringDto;
	}
}
