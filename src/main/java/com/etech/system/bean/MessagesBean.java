package com.etech.system.bean;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;

public class MessagesBean {
	
	private Map<String, String> errMap = new LinkedMap();
	private Map<String, String> msgMap = new LinkedMap();
	
	public MessagesBean addError(Locale locale, MessageSource messageSource, String key, String[] args) {
		errMap.put(key, messageSource.getMessage(key, args, locale));
		return this;
	}
	
	public MessagesBean addMessage(Locale locale, MessageSource messageSource, String key, String[] args) {
		msgMap.put(key, messageSource.getMessage(key, args, locale));
		return this;
	}
	
	public String getAllMessage() {
		StringBuilder buf = new StringBuilder();
		for (String s : errMap.values()) {
			if (buf.length()>0)
				buf.append("\r\n");
			buf.append(s);
		}
		return buf.toString();
	}
	
	public void setErrorsIntoModelView(ModelAndView mv) {
		//mv.addObject(HaConstants.LOGIC_CHECK_ERRORS, getAllMessage());
		mv.addObject(HaConstants.LOGIC_CHECK_ERRORS, errMap.values());
	}
	
	public void setMessagesIntoModelView(ModelAndView mv) {
		mv.addObject(HaConstants.LOGIC_MESSAGE, msgMap.values());
	}
	
	public boolean hasErrors() {
		if (errMap.size()>0)
			return true;
		return false;
	}
}
