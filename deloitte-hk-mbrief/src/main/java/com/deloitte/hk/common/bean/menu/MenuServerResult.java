package com.deloitte.hk.common.bean.menu;

import java.io.Serializable;

/**
 * 
* Rest服务返回结果类
* @ClassName: RestServerResult
* @author zhangdie
 */
public class MenuServerResult implements Serializable {

	/**
	 * 返回结果 true：正常 false：异常
	 */
	private boolean success;
	
	/**
	 * 错误原因 1: 未授权 2: 登录错误
	 */
	private int failno;

	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 提示信息，如果 success 为 true, 则是成功提示信息；
	 * 如果 success 为 false, 则是异常提示信息。
	 */
	private String message;

	/**
	 * 构造方法
	 * 
	 * @author 
	 * @param result
	 *            返回结果 0：正常 -1：异常
	 * @param data
	 *            返回数据
	 * @param message
	 *            提示信息
	 */
	public MenuServerResult(boolean success, Object data, String message) {
		this.success = success;
		this.data = data;
		this.message = message;
	}

	public MenuServerResult() {
	}

	/**
	 * @return the 返回返回数据
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            设置返回数据
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the 返回成功或异常提示信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param errorMsg
	 *            设置成功或异常提示信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	
	/**
	 * get success
	 * @return success
	 */
	public boolean getSuccess()
	{
		return success;
	}

	/** 
	 * set success
	 * @param success the success to set
	 */
	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public int getFailno() {
		return failno;
	}

	public void setFailno(int failno) {
		this.failno = failno;
	}

	/**
     * 放入返回客户端数据
     * 
     * @param result
     *            返回结果 0：正常 -1：异常
     * @param data
     *            返回数据
     * @param errorMsg
     *            异常信息
     * @return Rest服务返回结果类
     */
    public static MenuServerResult putRestServerResult(boolean success, Object data, String errorMsg) {
        return new MenuServerResult(success, data, errorMsg);
    }

	public void setResult(int i)
	{
		// TODO Auto-generated method stub
		
	}

}
