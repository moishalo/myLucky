package com.chinasofti.lucky.ld;

import org.apache.commons.lang.StringUtils;

/**
 * @Title: LuckyDog.java
 * @Package com.chinasofti.lucky.ld
 * @Description: 抽奖参与者
 * @author moishalo.zhang moishalo.zhang@gmail.com
 * @date 2014年1月5日 下午5:29:55
 * @version V1.0
 */
public class LuckyDog {
	private String code;
	private String name;
	private String org;
	private String phone;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public boolean equals(Object obj) {
		return StringUtils.equalsIgnoreCase(this.code, ((LuckyDog)obj).getCode());
	}
	
	/* (非 Javadoc)
	*
	Title: hashCode
	*
	Description: 参与者比较通过编码比较，编码唯一标志一个参与者
	* @return
	* @see java.lang.Object#hashCode()
	*/
	@Override
	public int hashCode() {
		return this.code.hashCode();
	}
	
}
