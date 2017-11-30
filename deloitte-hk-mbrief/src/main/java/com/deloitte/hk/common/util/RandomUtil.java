package com.deloitte.hk.common.util;

import java.util.Random;

public class RandomUtil {

	public static int randomInt(int len) {
		if (len > 0) {
			String max = "";
			for (int i = 0; i < 4; i++) {
				max = max + "9";
			}
			return new Random().nextInt(Integer.parseInt(max));
		} else {
			return 0;
		}		
	}
	
	public static String randomStr(int len) {
		String result="";  
        for(int i=0;i<len;i++){  
            int intVal=(int)(Math.random()*26+97);  
            result=result+(char)intVal;  
        }
        return result;
	}
}
