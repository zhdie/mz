package com.deloitte.hk.common.util;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstConf {
	
	private static Logger logger = LoggerFactory.getLogger(ConstConf.class);
	
	public static int pageSize = 100;
	
	public static int token_expires;
	
	static {
		try {
			Properties prop = SystemPropertiesUtil.loadExtProperties("application.properties");			
			String token_expirestr =  SystemPropertiesUtil.getProperty("hk", "token_expires");
			try {
				token_expires = Integer.parseInt(token_expirestr) * 60 * 1000;
			} catch (Exception e) {
				token_expirestr =  SystemPropertiesUtil.getProperty("application", "token_expires");
				try {
					token_expires = Integer.parseInt(token_expirestr) * 60 * 1000;
				} catch (Exception e1) {
					logger.error("", e1);
					token_expires = 0;
				}			
			}	
		} catch (Exception e) {
			
		}
	}

	public static int getToken_expires() {
//		return 60;		
		return token_expires;
	}

	
}
