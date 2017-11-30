/**
 * Project Name:HuiKuangPay
 * File Name:JsonUtil.java
 * Package Name:com.zte.iups.pay.huikuang.json
 * Date:2016-3-31下午5:07:53
 * Copyright (c) 2016, zhangdie All Rights Reserved.
 *
*/

package com.deloitte.hk.common.util.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * ClassName:JsonUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2016-3-31 下午5:07:53
 * @author   zhangdie
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);	
	
	public static <T> T JsonToObj(String json, Class<T> clazz) {
		T obj;
		if (null == json || null == clazz) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ObjectReader reader = mapper.reader(clazz);
			obj = reader.readValue(json);
			return obj;
		} catch (JsonProcessingException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static String ObjToJson(Object obj) {		
		try {
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithType(obj.getClass());
			String json = writer.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static <T> List<T> JsonToList(String json, Class listClazz, Class<T> eleClazz) {
		List<T> list = null;		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JavaType javatype = mapper.getTypeFactory().constructParametricType(listClazz, eleClazz);
			list = mapper.readValue(json, javatype);
		} catch (JsonParseException e) {
			logger.error("", e);
		} catch (JsonMappingException e) {
			logger.error("", e);
		} catch (Exception e) {
			logger.error("", e);
		}
		return list;
	}
	
	public static Map JsonToMap(String json) {
		Map map = null;
		if (null == json) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			logger.error("", e);
		} catch (JsonMappingException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		} catch (Exception e) {
			logger.error("", e);
		}
		return map;
	}
	
}
