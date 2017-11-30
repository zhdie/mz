package com.deloitte.hk.common.token;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
	
	private static TokenManager instance;
	
	
	public static int VALID_EXIST_ONLY = 1;
	
	/*
	 * Default policy
	 */
	public static int VALID_APP_USER_ID = 2;
	
	public static int VALID_EXPIRES = 3;
	
	public static int VALID_APP_USER_ID_EXPIRES = 4;

	/*
	 * token buffer. key: token, value: token
	 */
	private static volatile Map<String,Token> tokenMap = new ConcurrentHashMap<String,Token>();
	
	/*
	 * token buffer. key: appid + "|" + userid, value: token
	 */
	private static volatile Map<String,Token> useridMap = new ConcurrentHashMap<String,Token>();
	
	private TokenManager() {}
	
	public static TokenManager getInstance() {
		if (null == instance) {
			instance = new TokenManager();
		}
		return instance;
	}
	
	public Token newToken(String appid, String userid, long expires) throws UnsupportedEncodingException {
		String tokenstr = TokenSimpleUtils.getToken(appid, userid);
		Token token = new Token(tokenstr, appid, userid, expires);
		setToken(token);
		return token;
	}
	
	public Token newToken(String appid, String userid, String username, long expires) throws UnsupportedEncodingException {
		String tokenstr = TokenSimpleUtils.getToken(appid, userid);
		Token token = new Token(tokenstr, appid, userid, username, expires);
		setToken(token);
		return token;
	}
	
	public Token newToken(String appid, String userid, String username, String passwrod, long expires) throws UnsupportedEncodingException {
		String tokenstr = TokenSimpleUtils.getToken(appid, userid);
		Token token = new Token(tokenstr, appid, userid, username, passwrod, expires);
		setToken(token);
		return token;
	}
	
	/**
	 * 
	 * @param token token string.
	 * @param appid application id.
	 * @param userid user id.
	 * @param isExpires  true: valid expires time, false: no valid expires
	 * @return
	 * @throws IOException
	 */
	public boolean validToken(String token, String appid, String userid, boolean isExpires) throws IOException {
//		String str = appid + userid;
		Token tk = tokenMap.get(token);
		if (null == tokenMap.get(token)) {
			return false;
		} else {
			if (isExpires) {
				if (tk.isAvailable()) {
					return tk.getToken().equals(token);
				} else {
					return false;
				}
			} else {
				return tk.getToken().equals(token);
			}
		}		
	}
	
	/**
	 * 
	 * @param token token string
	 * @param isExpires true: valid expires time, false: no valid expires
	 * @return
	 * @throws IOException
	 */
	public boolean validToken(String token, boolean isExpires) throws IOException {
		if (null == token) {
			return false;
		}
		Token tk = tokenMap.get(token);
		if (null == tk) {
			return false;
		} else {
			if (isExpires) {
				return tk.isAvailable();
			} else {
				return true;
			}
		}	
	}
	
	/**
	 * 
	 * @param token token string
	 * @param isExpires true: valid expires time, false: no valid expires
	 * @return
	 * @throws IOException
	 */
	public boolean validToken(String token, String userid, boolean isExpires) throws IOException {
		if (null == token) {
			return false;
		}
		Token tk = tokenMap.get(token);
		if (null == tk && tk.getUserid().equals(userid)) {
			return false;
		} else {
			if (isExpires) {
				return tk.isAvailable();
			} else {
				return true;
			}
		}	
	}
	
	/**
	 * 
	 * @param key: appid + "|" + userid
	 * @return
	 */
	public Token getToken(String appid, String userid){
		return useridMap.get(appid + "|" + userid);
	}
	
	/**
	 * 
	 * @param tokenstr
	 * @return
	 */
	public Token getToken(String tokenstr) {
		if (null != tokenstr) {
			return tokenMap.get(tokenstr);
		} else {
			return null;
		}		
	}
	
	/**
	 * 设置token to buffer
	 * @param token
	 */
	public void setToken(Token token) {
		if (null != token) {
			remove(token.getAppid(), token.getUserid());
			useridMap.put(token.getAppid() + "|" + token.getUserid(), token);
			tokenMap.put(token.getToken(), token);
		}
		
	}
	
	public void clear(){
		useridMap.clear();
		tokenMap.clear();
	}
	
	public void remove(String appid, String userid) {
		Token token = getToken(appid, userid);
		if (null != token) {
			useridMap.remove(appid + "|" + userid);
			tokenMap.remove(token.getToken());
		}		
	}
	
	public void remove(String tokenstr) {
		Token token = getToken(tokenstr);
		if (null != token) {
			useridMap.remove(token.getAppid() + "|" + token.getUserid());
			tokenMap.remove(tokenstr);
		}	
	}
	
	
}
