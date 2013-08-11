package com.etech.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.etech.ha.constants.HaConstants;
import com.etech.system.bean.UserInfo;

public class BaseController {
	
	@Autowired
	protected MessageSource messageSource;
	
	public int accessRight(UserInfo userInfo, String action) {
		
		//TODO
		
		return HaConstants.ACCESS_OK;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
}
