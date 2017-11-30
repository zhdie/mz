/**
 * Project Name:OMS
 * File Name:UUIDutil.java
 * Package Name:com.zd.oms.common.util
 * Date:2016-5-18下午2:25:49
 * Copyright (c) 2016, zhangdie All Rights Reserved.
 *
*/

package com.deloitte.hk.common.util;

import java.util.UUID;

/**
 * ClassName:UUIDutil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2016-5-18 下午2:25:49
 * @author   zhangdie
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class UUIDutil {

	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	
	/**
	 * main:(这里用一句话描述这个方法的作用).
	 * TODO(这里描述这个方法的使用方法 – 可选).
	 *
	 * @author zhangdie
	 * @param args
	 * @since JDK 1.6
	 */
//	public static void main(String[] args) {
//		System.out.println(getUUID());
//		System.out.println(getUUID().length());
//	}

}
