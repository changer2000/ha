package com.etech.system.controller;

import com.etech.ha.constants.HaConstants;
import com.etech.system.bean.UserInfo;

public class BaseController {
	
	public int accessRight(UserInfo userInfo, String action) {
		
		//TODO
		
		return HaConstants.ACCESS_OK;
	}
	
}
