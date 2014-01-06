package com.chinasofti.lucky.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chinasofti.lucky.box.LuckyBox;
import com.chinasofti.lucky.ld.LuckyDog;
import com.chinasofti.lucky.ld.LuckyDogProvider;

/**
 * @Title: LuckyController.java
 * @Package com.chinasofti.lucky.mvc
 * @Description: 抽奖系统的Controller
 * @author moishalo.zhang moishalo.zhang@gmail.com
 * @date 2014年1月5日 下午7:51:31
 * @version V1.0
 */
@Controller
@RequestMapping(value = "/")
public class LuckyController {
	Logger logger = LoggerFactory.getLogger(LuckyController.class);

	/**
	 * @Title: index
	 * @Description: 进入主页
	 * @return 参数及返回值
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		logger.debug("进入主页");
		return "home";
	}

	/**
	 * @Title: getLuckyBox
	 * @Description: 获取抽奖箱内的参与者信息，每次获取前都进行一次洗牌
	 * @return 参数及返回值
	 * @return Object[] 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/luckyBox", method = RequestMethod.GET)
	public Object[] getLuckyBox() {
		logger.debug("进入获取抽奖参与者");
		LuckyBox box = LuckyBox.getLuckyBox();
		box.shuffle();
		box.print();
		return box.getLuckyDogs().toArray();
	}

	/**
	 * @Title: addPage
	 * @Description: 进入到导入抽奖参与者界面
	 * @return 参数及返回值
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage() {
		logger.debug("进入抽奖人员导入功能");
		return "add";
	}

	/**
	 * @Title: addLuckyDogs
	 * @Description: 导入抽奖参与者信息
	 * @param luckyDogs
	 * @return 参数及返回值
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public ModelAndView addLuckyDogs(@RequestParam(value="luckyDogs", required=false) MultipartFile luckyDogs) {
		LuckyBox box = LuckyBox.getLuckyBox();
		try {
			box.addAll(provider.get(luckyDogs.getInputStream()));
		} catch (IOException e) {
			logger.error("导入抽奖人员出错",e);
		}
		box.shuffle();
		box.print();
		ModelAndView view = new ModelAndView();
		view.setViewName("add");
		view.addObject("flag", "true");
		return view;
	}

	/**
	 * @Title: removePage
	 * @Description: 进入移除抽奖参与者页面
	 * @return 参数及返回值
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String removePage() {
		logger.debug("进入抽奖人员移除功能");
		return "remove";
	}

	/**
	 * @Title: removeLuckyDogs
	 * @Description: 批量移除抽奖参与者
	 * @param luckyDogs
	 * @return 参数及返回值
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public ModelAndView removeLuckyDogs(@RequestParam(value="luckyDogs", required=false) MultipartFile luckyDogs) {
		LuckyBox box = LuckyBox.getLuckyBox();
		try {
			box.removeAll(provider.get(luckyDogs.getInputStream()));
		} catch (IOException e) {
			logger.error("移除抽奖人员出错",e);
		}
		box.shuffle();
		box.print();
		ModelAndView view = new ModelAndView();
		view.setViewName("remove");
		view.addObject("flag", "true");
		return view;
	}

	/**
	 * @Title: luckyPage
	 * @Description: 进入抽奖页面
	 * @return 参数及返回值
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/getLuckyPage", method = RequestMethod.GET)
	public String luckyPage() {
		logger.debug("进入抽奖功能");
		return "getLucky";
	}

	/**
	 * @Title: getLucky
	 * @Description: 移除一个抽奖参与者(一般情况下就是中奖的那个混蛋或者没来的小可怜)
	 * @param luckyDog
	 * @return 参数及返回值
	 * @return String 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getLucky", method = RequestMethod.POST)
	public String getLucky(LuckyDog luckyDog) {
		LuckyBox box = LuckyBox.getLuckyBox();
		box.remove(luckyDog);
		box.shuffle();
		box.print();
		return "getLucky";
	}

	private LuckyDogProvider provider;

	@Autowired
	@Required
	public void setProvider(LuckyDogProvider provider) {
		this.provider = provider;
	}
}
