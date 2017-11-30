package com.deloitte.hk.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class SystemPropertiesUtil {
	
	private static Map<String, Properties> props;
	
	public static String suffix = ".properties";
	
	public static String active_mode = null;
	
	public static String MODE_DEV = "dev";
	
	public static String MODE_TEST = "test";
	
	public static String MODE_DEPLOY = "deploy";
	
	static{
		try{
			Properties appProp = loadProperties("application.properties");
			active_mode = appProp.getProperty("spring.profiles.active");
			String basicName = "hk.properties";
			props = new HashMap<String, Properties>();
			loadExtProperties(basicName);
		}catch(Exception e){
			e.printStackTrace();		
		}
	}
	
	/**
	 * 加载属性文件, 加载后, 执行putProperties() 放入内存
	 * @param modeName
	 * @return
	 */
	public static Properties loadExtProperties(String basicName) {
		String modeName = getModeName(basicName);
		Properties prop = null;
		InputStream is = null;
		try {	
			File f = new File("");
			String abspath = f.getAbsolutePath();
//			File pf = new File(abspath + "/config/" + modeName);
			File pf = new File(abspath + "/" + modeName);
			if (pf.exists()) {
				// dev mode, project root path
				prop = new Properties();
				is = new FileInputStream(pf);
				prop.load(new InputStreamReader(is,"UTF-8"));
			} else {
				// if root path not file, to parent path
				prop = new Properties();
				pf = new File(toParentPath(abspath) + modeName);				
				if (pf.exists()) {
					is = new FileInputStream(pf);
					prop.load(new InputStreamReader(is,"UTF-8"));
				} else {
					is = SystemPropertiesUtil.class.getClassLoader().getResourceAsStream(modeName);
					if (null != is) {
						prop.load(new InputStreamReader(is,"UTF-8"));
					}					
				}
			}
			String prename = basicName;
			if (basicName.endsWith(suffix)) {				
				prename = basicName.substring(0, basicName.length() - suffix.length());
			}
			putProperties(prename, prop);
		 	return prop;
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 } finally {
			 if (null != is) {
				 try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
	}
	
	/**
	 * 在 loadExtProperties() 加载属性文件后配对使用
	 * @param tag
	 * @param props
	 */
	private static void putProperties(String tag, Properties prop) {
		if (null != tag && null != prop) {
			props.put(tag, prop);
		}
	}
	
	public static String getProperty(String tag, String key) {
		if (null != props) {
			Properties prop = props.get(tag);
			if (null != prop) {
				return prop.getProperty(key);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static String getProperty(String key){
		if (null != props) {
			for (Properties prop : props.values()) {
				String value = prop.getProperty(key);
				if (null != value) {
					return value;
				}
			}
			return null;
		} else {
			return null;
		}
	}
	
	public static void putProterty(String tag, String key, String value) {
		Properties prop = props.get(tag);
		if (null == prop) {
			prop = new Properties();
			prop.setProperty(key, value);
			props.put(tag, prop);
		} else {
			prop.setProperty(key, value);
		}		
	}
	
	/**
	 * 根据基本文件名得到对应部署模式的配置文件名
	 * @param basicName 如order.properties
	 * @return modeName 如order-dev.properties
	 */
	private static String getModeName(String basicName) {
		String modeName = basicName;
		if (null != basicName) {
			if (basicName.endsWith(suffix)) {				
				String prename = basicName.substring(0, basicName.length() - suffix.length());
				modeName = prename + "-" + active_mode + suffix;
				return modeName;
			} else {
				return modeName;
			}
		} else {
			return null;
		}
		
	}

	private static Properties loadProperties(String fname) {
		Properties prop = null;
		InputStream is = null;
		try {	
			File f = new File("");
			String abspath = f.getAbsolutePath();
			File pf = new File(abspath + "/" + fname);
			if (pf.exists()) {
				// dev mode, project root path
				prop = new Properties();
				is = new FileInputStream(pf);
				prop.load(is);
			} else {
				// if root path not file, to parent path
				prop = new Properties();
				pf = new File(toParentPath(abspath) + fname);
				if (pf.exists()) {
					is = new FileInputStream(pf);
					prop.load(is);
				} else {
					is = SystemPropertiesUtil.class.getClassLoader().getResourceAsStream(fname);
//					is = SystemPropertiesUtil.class.getClass().getResourceAsStream("/application.properties");
					if (null != is) {
						prop.load(is);
					}					
				}
				
			}
		 	return prop;
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 } finally {
			 if (null != is) {
				 try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
	}
	
	private static String toParentPath(String path) {
		String start = "";
		String end = "";
		if (path.endsWith("/")) {
			path = path.substring(0, path.length() -1);
			end = "/";
		}
		if (path.startsWith("/")) {
			path = path.substring(1, path.length());
			start = "/";
		}
		
		String[] subpaths = path.split("/");
		String ppath = start;
		for (int i = 0; i < subpaths.length - 1; i++) {
			ppath = ppath + subpaths[i] + "/";
		}
		if (!ppath.endsWith("/")) {
			ppath = ppath + "/";
		}
		return ppath;
	}
}
