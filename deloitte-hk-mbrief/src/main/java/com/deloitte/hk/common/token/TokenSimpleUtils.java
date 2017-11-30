/**
 * Project Name:WebTest
 * File Name:TokenUtils.java
 * Package Name:tocken
 * Date:2016-8-16下午5:27:01
 * Copyright (c) 2016, zhangdie All Rights Reserved.
 *
*/

package com.deloitte.hk.common.token;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.deloitte.hk.common.util.RandomUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 * ClassName:TokenUtils
 * TODO
 * Date:     2016-8-16 下午5:27:01
 * @author   zhangdie
 */
public class TokenSimpleUtils {
	private static final String privateKey = "8e618bab!864f_40d6.a2e5(6fb40fe97472";
	 
	public static final String adminRole = "0001";//admin
	
	public static final String consumerRole = "0002";//consumer
	
	private static String genToken(String str) {
        return (Hashing.md5().newHasher().
                putString(str, Charsets.UTF_8).
                putString(privateKey, Charsets.UTF_8).hash().toString() + RandomUtil.randomInt(4));
    }	
	
	/**
	 * get token.
	 * @param appid: application id.
	 * @param userid: user id.
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getToken(String appid, String userid) throws UnsupportedEncodingException {
		String str = appid + userid;
        return genToken(str);
    }
	
	/**
	 * valid token
	 * @param token: token string.
	 * @param appid: application id.
	 * @param userid: user id.
	 * @return
	 * @throws IOException
	 */
	public static boolean validToken(String token, String appid, String userid) throws IOException {
		String str = appid + userid;
		return valid(token, str);
	}
	
	private static boolean valid(String token, String str) {
        String confirm = genToken(str);
        if (confirm.substring(0, confirm.length() - 4).equals(token.substring(0, token.length() - 4))) {
            return true;
        } else {
            return false;
        }
    }
	
}
