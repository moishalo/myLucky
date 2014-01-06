package com.chinasofti.lucky.ld;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName: LuckyDogProvider
 * @Description: 抽奖人员信息提取器接口
 * @author moishalo moishalo.zhang@gmail.com
 * @date 2014年1月5日 下午5:34:51
 *
 */
public interface LuckyDogProvider {
	/**
	 * @Title: get
	 * @Description: 获取抽奖参与者信息
	 * @return 参数及返回值
	 * @return List<LuckyDog> 返回类型
	 * @throws
	 */
	public List<LuckyDog> get(InputStream in);
}
