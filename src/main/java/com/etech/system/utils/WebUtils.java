package com.etech.system.utils;

import org.apache.commons.lang.StringUtils;

import com.etech.ha.constants.HaConstants;
import com.etech.system.bean.UserInfo;

public class WebUtils {
	
	public static void setSessionAttribute(UserInfo userInfo,
			String subSystem, String module, String key, Object value) {
		userInfo.getSessionMap().put(generateKey(subSystem, module, key), value);
	}
	
	public static Object getSessionAttribute(UserInfo userInfo,
			String subSystem, String module, String key) {
		return userInfo.getSessionMap().get(generateKey(subSystem, module, key));
	}
	
	public static Object removeSessionAttribute(UserInfo userInfo,
			String subSystem, String module, String key) {
		return userInfo.getSessionMap().remove(generateKey(subSystem, module, key));
	}
	
	public static String generateKey(String subSystem, String module, String key) {
		StringBuilder buf = new StringBuilder();
		buf.append(StringUtils.stripToEmpty(subSystem))
			.append(HaConstants.SESSION_SEPERATOR_CHAR)
			.append(StringUtils.stripToEmpty(module))
			.append(HaConstants.SESSION_SEPERATOR_CHAR)
			.append(key);
		return buf.toString();
		
	}

}
