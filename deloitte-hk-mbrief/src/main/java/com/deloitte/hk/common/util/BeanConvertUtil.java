package com.deloitte.hk.common.util;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Bean Util
 * @author deezhang
 *
 */
public class BeanConvertUtil {

	/**
	 * Copy source object property to target object
	 * @param src Source Object
	 * @param target Target Object
	 * @param excludeFds Exclude fields name(case sensitive)
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void copy(Object src, Object target, List<String> excludeFds) throws IllegalArgumentException, IllegalAccessException {
		if (null == src) {
			return;
		}
		Class clazz1 = src.getClass();
		Class clazz2 = target.getClass();
		Field[] fs1 = clazz1.getDeclaredFields();
		Field[] fs2 = clazz2.getDeclaredFields();
		Field[] fs22 = clazz2.getSuperclass().getDeclaredFields();
		if (fs1.length >= 1) {
			for (int j = 0; j < fs1.length; j++) {
				Field field1 = fs1[j];
				field1.setAccessible(true);
				Object value = field1.get(src);
				if (null != value && !"".equals(value)) {
					String fn = field1.getName().toUpperCase();
					if (null != excludeFds && !excludeFds.contains(field1.getName())) {
						if (fs2.length >= 1) {
							for (int n = 0; n < fs2.length; n++) {
								Field field2 = fs2[n];
								if (fn.equals(field2.getName().toUpperCase())) {
									try {
										field2.setAccessible(true);
										field2.set(target, value);
										field2.setAccessible(false);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
						if (fs22.length >= 1) {
							for (int n = 0; n < fs22.length; n++) {
								Field field22 = fs22[n];
								if (fn.equals(field22.getName().toUpperCase())) {
									try {
										field22.setAccessible(true);
										field22.set(target, value);
										field22.setAccessible(false);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
					} //end judge excludeFds		
				}//end not null
			}
		}
	}
}
