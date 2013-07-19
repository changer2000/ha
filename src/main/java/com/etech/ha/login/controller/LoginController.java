package com.etech.ha.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.login.service.UserService;
import com.etech.ha.peer.UserPeer;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.do")
	public ModelAndView doLogin(HttpServletRequest request, UserPeer user) {
		boolean rs = userService.checkLogin(user);
		if (rs) {
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		} else {
			return new ModelAndView("index", "error", "用户名密码错误");
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
