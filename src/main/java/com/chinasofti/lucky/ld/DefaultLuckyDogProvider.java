package com.chinasofti.lucky.ld;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @ClassName: DefaultLuckyDogProvider
 * @Description: properties文件保存的抽奖参与者信息获取
 * @author moishalo moishalo.zhang@gmail.com
 * @date 2014年1月5日 下午5:29:12
 *
 */
@Component("provider")
public class DefaultLuckyDogProvider implements LuckyDogProvider {
	
	Logger logger = LoggerFactory.getLogger(DefaultLuckyDogProvider.class);

	/* (非 Javadoc)
	*
	Title: get
	*
	Description: 通过properties文件中读取抽奖参与者名单
	
	* @param in
	* @return
	* @see com.chinasofti.lucky.ld.LuckyDogProvider#get(java.io.InputStream)
	*/
	public List<LuckyDog> get(InputStream in) {

		Properties properties = new Properties();

		List<LuckyDog> result = new LinkedList<LuckyDog>();
		try {
			properties.load(in);

			Iterator it = properties.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entity = (Entry<String, String>) it
						.next();
				String key = entity.getKey();
				String[] values = entity.getValue().split(",");

				String name = "";
				String org = "";
				String phone = "";
				if (1 > values.length) {
					RuntimeException toThrow = new RuntimeException("抽奖者至少拥有名称属性");
					logger.error("抽奖者至少拥有名称属性",toThrow);
					throw toThrow;
				}
				name = values[0];
				if (1 < values.length)
					org = values[1];
				if (2 < values.length)
					phone = values[2];

				LuckyDog luckyDog = new LuckyDog();
				luckyDog.setCode(key);
				luckyDog.setName(name);
				luckyDog.setOrg(StringUtils.isBlank(org) ? "" : org);
				luckyDog.setPhone(StringUtils.isBlank(phone) ? "" : phone);
				result.add(luckyDog);
			}
		} catch (FileNotFoundException e) {
			logger.error("读取抽奖人员信息失败",e);
		} catch (IOException e) {
			logger.error("读取抽奖人员信息失败",e);
		}
		return result;
	}

}
