package com.chinasofti.lucky.box;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.lucky.ld.LuckyDog;


/**
 * @ClassName: LuckyBox
 * @Description: 抽奖箱
 * @author moishalo moishalo.zhang@gmail.com
 * @date 2014年1月5日 下午5:31:13
 *
 */
public class LuckyBox {
	
	Logger logger = LoggerFactory.getLogger(LuckyBox.class);
	
	private static LuckyBox box = new LuckyBox();
	
	private List<LuckyDog> contents = new LinkedList<LuckyDog>();

	private LuckyBox() {
	}

	/**
	 * @Title: getLuckyBox
	 * @Description: 单例获取抽奖箱
	 * @return 参数及返回值
	 * @return LuckyBox 返回类型
	 * @throws
	 */
	public static LuckyBox getLuckyBox(){
		return box;
	}
	
	/**
	 * @Title: getLuckyDogs
	 * @Description: 获取抽奖箱内所有人员
	 * @return 参数及返回值
	 * @return List<LuckyDog> 返回类型
	 * @throws
	 */
	public List<LuckyDog> getLuckyDogs(){
		return contents;
	}
	
	/**
	 * @Title: addAll
	 * @Description: 添加参数中所有的参与者，并且去掉重复的
	 * @param additions 参数及返回值
	 * @return void 返回类型
	 * @throws
	 */
	public void addAll(Collection<LuckyDog> additions){
		Collection retain = CollectionUtils.retainAll(contents, additions);
		this.contents.removeAll(retain);
		this.contents.addAll(additions);
	}
	
	/**
	 * @Title: removeAll
	 * @Description: 移除参数中所有的参与者，并去掉重复的
	 * @param toRemove 参数及返回值
	 * @return void 返回类型
	 * @throws
	 */
	public void removeAll(Collection<LuckyDog> toRemove){
		this.contents.removeAll(toRemove);
	}
	
	/**
	 * @Title: remove
	 * @Description: 删除一个参与者
	 * @param obj 参数及返回值
	 * @return void 返回类型
	 * @throws
	 */
	public void remove(Object obj){
		this.contents.remove(obj);
	}
	
	/**
	 * @Title: shuffle
	 * @Description: 抽奖箱内洗牌
	 * @return void
	 * @throws
	 */
	public void shuffle(){
		synchronized (this.contents) {
			Collections.shuffle(contents, new Random(System.nanoTime()));
		}
	}
	
	/**
	 * @Title: print
	 * @Description: 调试信息，按顺序查看抽奖箱内所有人员
	 * @return void 返回类型
	 * @throws
	 */
	public void print() {
		int total = 0;
		for(LuckyDog luckyDog : contents){
			logger.info("code:"+luckyDog.getCode());
			logger.info("name:"+luckyDog.getName());
			logger.info("org:"+luckyDog.getOrg());
			logger.info("phone:"+luckyDog.getPhone());
			logger.info("");
			total++;
		}
		logger.info("total:"+total);
	}
}
