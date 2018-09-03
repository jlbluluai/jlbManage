package com.xyz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 主要的工具类
 * 
 * @author 叶灬黎
 *
 */
public final class Utils {

	private static Logger logger = Logger.getLogger(Utils.class);

	/**
	 * 判断字符串是否符合手机号码格式
	 * 
	 * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
	 * 联通号码段:130、131、132、136、185、186、145 电信号码段:133、153、180、189
	 * 
	 * @author 叶灬黎
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
		return phone.matches(regex);
	}

	/**
	 * 判断字符串是否符合邮箱格式
	 * 
	 * @author 叶灬黎
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return email.matches(regex);
	}

	/**
	 * 判断一个字符串是否在一个字符串数组中
	 * 
	 * @author 叶灬黎
	 * @param str
	 * @param s
	 * @return
	 */
	public static boolean isThisStringGroup(String[] str, String s) {
		List<String> list = Arrays.asList(str);
		if (list.contains(s)) {
			return true;
		}
		return false;
	}

	private static String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * show 方法简介 生成6位验证码
	 * 
	 * @author 叶灬黎
	 * @return 6位验证码
	 */
	public static String getVerCode() {
		StringBuffer code = new StringBuffer();
		Random random = new Random();
		int temp;
		for (int i = 0; i < 6; i++) {
			if (random.nextInt(2) == 0) {
				temp = random.nextInt(10);
				code.append(temp);
			} else {
				temp = random.nextInt(26);
				code.append(alphabet[temp]);
			}
		}
		return code.toString();
	}

	/**
	 * 有当前时间戳加上1000000以内随机数构建基本不会重复的Long型变量，主要是用作复杂的主键生成
	 * 
	 * @author 叶灬黎
	 * @return
	 */
	public static Long createComplexId() {
		return Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date()) + "" + new Random().nextInt(1000000));
	}

	/**
	 * 获取一个文件的类型
	 * 
	 * @param filename
	 * @return
	 */
	public static String getTheFileStyle(String filename) {
		String[] temp = filename.split("\\.");
		return temp[temp.length - 1];
	}

	/**
	 * show 方法简介 读取.properties文件
	 * 
	 * @author 叶灬黎
	 * @param file
	 *            要读取的.properties文件的路径
	 * @return Properties类对象
	 */
	public static Properties getProperties(String file) {
		Properties properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(file));
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			logger.error("加载属性文件出错");
		}
		return properties;
	}

}
