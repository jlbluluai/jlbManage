package com.xyz.controller;

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
import com.xyz.domain.Pubboard;
import com.xyz.domain.Recommend;
import com.xyz.domain.RecommendCategory;
import com.xyz.domain.User;
import com.xyz.domain.Version;
import com.xyz.dto.StringDto;
import com.xyz.service.PubboardService;
import com.xyz.service.RecommendCategoryService;
import com.xyz.service.RecommendService;
import com.xyz.service.VersionService;

@Controller
public class FrontMantainController {

	// 日志
	private static Logger logger = Logger.getLogger(FrontMantainController.class);

	// 引入服务
	@Autowired
	@Qualifier("pubboardService")
	private PubboardService pubboardService;

	@Autowired
	@Qualifier("versionService")
	private VersionService versionService;

	@Autowired
	@Qualifier("recommendCategoryService")
	private RecommendCategoryService recommendCategoryService;

	@Autowired
	@Qualifier("recommendService")
	private RecommendService recommendService;

	/* 登录公板 */

	/**
	 * 获取登录公板内容
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getThePubboard", method = RequestMethod.GET)
	@ResponseBody
	public Pubboard getThePubboard() {
		Pubboard pubboard = new Pubboard();
		logger.info(CommonElement.separator + "获取登录公板开始" + CommonElement.separator);

		pubboard = pubboardService.getAppointedItem2(null);

		logger.info(CommonElement.separator + "获取登录公板结束" + CommonElement.separator);
		return pubboard;
	}

	/**
	 * 发布新的登录公板
	 * 
	 * @param title
	 * @param content
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveOnePubboard", method = RequestMethod.POST)
	@ResponseBody
	public StringDto saveOnePubboard(@RequestParam("title") String title, @RequestParam("content") String content,
			HttpSession session) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "登录公板开始" + CommonElement.separator);

		User u = (User) session.getAttribute("user");

		Pubboard pubboard = new Pubboard();

		pubboard.setTitle(title);
		pubboard.setContent(content);
		pubboard.setLeaveTime(new Date());
		pubboard.setStatus((byte) 0);
		pubboard.setUid(u.getId());

		String msg = "";

		if (pubboardService.saveAppointedItem(pubboard)) {
			msg = "yes";
		} else {
			msg = "发布失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "登录公板结束" + CommonElement.separator);
		return stringDto;
	}

	/* 版本信息 */
	/**
	 * 获取历史版本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getVersions", method = RequestMethod.GET)
	@ResponseBody
	public List<Version> getVersions() {
		List<Version> list = null;
		logger.info(CommonElement.separator + "版本信息获取开始" + CommonElement.separator);

		list = versionService.getAll();

		logger.info(CommonElement.separator + "版本信息获取结束" + CommonElement.separator);
		return list;
	}

	/**
	 * 版本信息登记
	 * 
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/saveOneVersion", method = RequestMethod.POST)
	@ResponseBody
	public StringDto saveOneVersion(@RequestParam("title") String title, @RequestParam("content") String content) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "版本信息登记开始" + CommonElement.separator);

		Version version = new Version();
		version.setTitle(title);
		version.setContent(content);
		version.setCreateTime(new Date());

		String msg = "";

		if (versionService.saveAppointedItem(version)) {
			msg = "yes";
		} else {
			msg = "发布失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "版本信息登记结束" + CommonElement.separator);
		return stringDto;
	}

	/* 门户推荐 */
	/**
	 * 获取历史版本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getRecommends", method = RequestMethod.GET)
	@ResponseBody
	public List<RecommendCategory> getRecommends() {
		List<RecommendCategory> list = null;
		logger.info(CommonElement.separator + "推荐获取开始" + CommonElement.separator);

		list = recommendCategoryService.getAll();

		logger.info(CommonElement.separator + "推荐获取结束" + CommonElement.separator);
		return list;
	}

	/**
	 * 新增一个推荐板块
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveOneRecCat", method = RequestMethod.POST)
	@ResponseBody
	public StringDto saveOneRecCat(@RequestParam("name") String name) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "新增一个推荐板块开始" + CommonElement.separator);

		RecommendCategory recommendCategory = new RecommendCategory();
		recommendCategory.setName(name);
		recommendCategory.setCreateTime(new Date());

		String msg = "";

		if (recommendCategoryService.saveAppointedItem(recommendCategory)) {
			msg = "yes";
		} else {
			msg = "新增失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "新增一个推荐板块结束" + CommonElement.separator);
		return stringDto;
	}

	/**
	 * 修改一个推荐板块
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changeOneRecCat", method = RequestMethod.POST)
	@ResponseBody
	public StringDto changeOneRecCat(@RequestParam("id") Integer id, @RequestParam("name") String name) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "修改一个推荐板块开始" + CommonElement.separator);

		RecommendCategory recommendCategory = new RecommendCategory();
		recommendCategory.setId(id);
		recommendCategory.setName(name);

		String msg = "";

		if (recommendCategoryService.changeAppointedItem(recommendCategory)) {
			msg = "yes";
		} else {
			msg = "修改失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "修改一个推荐板块结束" + CommonElement.separator);
		return stringDto;
	}

	/**
	 * 删除一个推荐板块
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cutOneRecCat", method = RequestMethod.POST)
	@ResponseBody
	public StringDto cutOneRecCat(@RequestParam("id") Integer id) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "删除一个推荐板块开始" + CommonElement.separator);

		String msg = "";

		if (recommendCategoryService.cutAppointedItem(id)) {
			msg = "yes";
		} else {
			msg = "删除失败";
		}

		stringDto.setMessage(msg);
		logger.info(CommonElement.separator + "删除一个推荐板块结束" + CommonElement.separator);
		return stringDto;
	}

	/**
	 * 新增一个推荐
	 * 
	 * @return
	 */
	@RequestMapping(value = "/saveOneRecommend", method = RequestMethod.POST)
	@ResponseBody
	public StringDto saveOneRecommend(@RequestParam("name") String name, @RequestParam("url") String url,
			@RequestParam("tag") String tag, @RequestParam("cid") Integer cid) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "新增一个推荐开始" + CommonElement.separator);

		Recommend recommend = new Recommend();
		recommend.setName(name);
		recommend.setUrl(url);
		recommend.setTag(tag);
		recommend.setStatus((byte) 1);
		recommend.setCid(cid);
		recommend.setCreateTime(new Date());

		String msg = "";

		if (recommendService.saveAppointedItem(recommend)) {
			msg = "yes";
		} else {
			msg = "新增失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "新增一个推荐结束" + CommonElement.separator);
		return stringDto;
	}

	/**
	 * 修改一个推荐
	 * 
	 * @return
	 */
	@RequestMapping(value = "/changeOneRecommend", method = RequestMethod.POST)
	@ResponseBody
	public StringDto changeOneRecommend(@RequestParam("name") String name, @RequestParam("url") String url,
			@RequestParam("tag") String tag, @RequestParam("id") Integer id) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "修改一个推荐开始" + CommonElement.separator);

		Recommend recommend = new Recommend();
		recommend.setId(id);
		recommend.setName(name);
		recommend.setUrl(url);
		recommend.setTag(tag);

		String msg = "";

		if (recommendService.changeAppointedItem(recommend)) {
			msg = "yes";
		} else {
			msg = "修改失败";
		}

		stringDto.setMessage(msg);

		logger.info(CommonElement.separator + "修改一个推荐结束" + CommonElement.separator);
		return stringDto;
	}

	/**
	 * 删除一个推荐
	 * 
	 * @return
	 */
	@RequestMapping(value = "/cutOneRecommend", method = RequestMethod.POST)
	@ResponseBody
	public StringDto cutOneRecommend(@RequestParam("id") Integer id) {
		StringDto stringDto = new StringDto();
		logger.info(CommonElement.separator + "删除一个推荐开始" + CommonElement.separator);

		String msg = "";

		if (recommendService.cutAppointedItem(id)) {
			msg = "yes";
		} else {
			msg = "删除失败";
		}

		stringDto.setMessage(msg);
		logger.info(CommonElement.separator + "删除一个推荐结束" + CommonElement.separator);
		return stringDto;
	}

}
