/**
* TODO
* @Project: TMS
* @Title: TmsException.java
* @Package com.zte.tms.common
* @author zhangdie
* @Date 2015-8-5 上午10:32:37 
* @Copyright: Copyright (c) 2015 ZTE Ltd. All Rights Reserved.  
* @Version v2.0.0
*/ 
package com.deloitte.hk.common.exception;

import org.springframework.util.StringUtils;

public class ServiceException extends RuntimeException {

	protected Throwable cause; // 传入的异常

	protected String message; // 错误描述信息

	protected String errorCode; // 系统错误编码

	/**
	 * 
	* 构造异常基类
	 */
	public ServiceException()
	{
		super();
	}

	/**
	 * .
	* 构造异常基类
	* @param message
	 */
	public ServiceException(String message)
	{
		super();
		this.message = message;
	}
	
	/**
	 * .
	* 构造异常基类
	* @param message
	 */
	public ServiceException(String errorCode, String message)
	{
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	/**
	 * .
	* 构造异常基类
	* @param cause
	 */
	public ServiceException(Throwable cause)
	{
		super(cause);
		this.cause = cause;
	}

	/**
	 * .
	* 构造异常基类
	* @param message
	* @param cause
	 */
	public ServiceException(String message, Throwable cause)
	{
		super(cause);
		this.message = message;
		this.cause = cause;
	}

	/**
	 * get cause
	 * 
	 * @return cause
	 */
	public Throwable getCause()
	{
		return cause;
	}

	/**
	 * set cause
	 * 
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause)
	{
		this.cause = cause;
	}

	/**
	 * get message
	 * 
	 * @return message
	 */
	public String getMessage()
	{
		String msg = this.message;
		if (StringUtils.isEmpty(msg)){
			msg = super.getMessage();
		} else if (!StringUtils.isEmpty(super.getMessage())) {
			msg = msg + super.getMessage();
		}
		return msg;
	}

	/**
	 * set message
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * get errorCode
	 * 
	 * @return errorCode
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * set errorCode
	 * 
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}
}
