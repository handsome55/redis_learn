package com.yh.common.constant;


/**
 * @date 2020-3-10
 * @author YuHao
 */
public enum Warning {

	/**
	 * 未知错误
	 */
	ERRTOKEN("授权失败", 401),

	/**
	 * 重新登录
	 */
	LOGIN_ERR("请重新登录", 403),

	/**
	 * 账号密码 错误
	 */
	ERRNAME_PWD("账号或密码错误", 405),
	
	/**
	 * 未知错误
	 */
	UNKNOWN("未知错误", 10000),

	/**
	 * 参数验证未通过
	 */
    PARAMETER_ERROR("参数验证未通过", 10001);


	protected String name;
	protected int value;

	 Warning(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
