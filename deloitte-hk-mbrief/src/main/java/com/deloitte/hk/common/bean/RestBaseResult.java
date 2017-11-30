package com.deloitte.hk.common.bean;

import java.io.Serializable;

import com.deloitte.hk.common.util.UUIDutil;

/**
 * 
* Rest服务返回结果类@ApiModel(value = "Rest response base")
* @ClassName: RestServerResult
* @author zhangdie
 */

//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RestBaseResult implements Serializable {
	
	/**
	 * 错误原因   0000: 成功,  0001: 未授权, 0002: 无效 token, 0003: 用户名或密码错误, 0004: 系统错误, 1000: 服务错误
	 */
	private String respcode;
	
	/**
	 * 提示信息，如果成功, 则是成功提示信息；
	 * 如果异常, 则是异常提示信息。
	 */
	private String message;
	
	private String respUUID = UUIDutil.getUUID();

	public String getRespcode() {
		return respcode;
	}

	public void setRespcode(String respcode) {
		this.respcode = respcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRespUUID() {
		return respUUID;
	}

	public void setRespUUID(String respUUID) {
		this.respUUID = respUUID;
	}

}
