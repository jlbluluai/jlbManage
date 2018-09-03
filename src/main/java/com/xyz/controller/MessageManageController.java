package com.xyz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.xyz.domain.Message;
import com.xyz.domain.User;
import com.xyz.dto.StringDto;
import com.xyz.service.MessageService;
import com.xyz.service.UserService;

@Controller
public class MessageManageController {

	// 日志
	private static Logger logger = Logger.getLogger(MessageManageController.class);

	// 引入服务
	@Autowired
	@Qualifier("messageService")
	private MessageService messageService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	/* 公告发放 */
	@RequestMapping(value = "/savePubMesssage", method = RequestMethod.POST)
	@ResponseBody
	public StringDto savePubMesssage(@RequestParam("subject") String subject, @RequestParam("content") String content,
			@RequestParam("object") String object, HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "公告发放开始" + CommonElement.separator);

		User u = (User) session.getAttribute("user");

		User user = new User();

		List<User> users = new ArrayList<>();

		if ("1".equals(object)) {
			user.setIid(2);
			List<User> list1 = userService.getUsersByIid(user);
			user.setIid(3);
			List<User> list2 = userService.getUsersByIid(user);

			users.addAll(list1);
			users.addAll(list2);
		} else if ("2".equals(object)) {
			user.setIid(3);
			users = userService.getUsersByIid(user);
		}


		List<Message> messages = new ArrayList<Message>();

		for (User uu : users) {
			Message message = new Message();
			message.setTitle(subject);
			message.setContent(content);
			message.setPid(u.getId());
			message.setStatus((byte) 0);
			message.setCreateTime(new Date());
			message.setRid(uu.getId());
			messages.add(message);
		}

		String msg = "";

		if (messageService.savePub(messages)) {
			msg = "yes";
		} else {
			msg = "发布失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "公告发放结束" + CommonElement.separator);
		return stringDto;
	}

}
