package com.etech.system.bean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;

public class MessagesBean {
	
	private Map<String, String> errMap = new LinkedMap();
	private Map<String, String> msgMap = new LinkedMap();
	
	public MessagesBean addError(HttpServletRequest req, MessageSource messageSource, String key, String[] args) {
		errMap.put(key, messageSource.getMessage(key, args, req.getLocale()));
		return this;
	}
	
	public MessagesBean addMessage(HttpServletRequest req, MessageSource messageSource, String key, String[] args) {
		msgMap.put(key, messageSource.getMessage(key, args, req.getLocale()));
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
}
