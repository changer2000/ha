package com.etech.system.bean;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;

public class MessagesBean {
	
	private Map<String, String> msgMap = new LinkedMap();
	
	public MessagesBean addMessage(HttpServletRequest req, MessageSource messageSource, String key, String[] args) {
		msgMap.put(key, messageSource.getMessage(key, args, req.getLocale()));
		return this;
	}
	
	public String getAllMessage() {
		StringBuilder buf = new StringBuilder();
		for (String s : msgMap.values()) {
			if (buf.length()>0)
				buf.append("\r\n");
			buf.append(s);
		}
		return buf.toString();
	}
	
	public void setIntoModelView(ModelAndView mv) {
		mv.addObject(HaConstants.LOGIC_CHECK_ERRORS, getAllMessage());
	}
}
