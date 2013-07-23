package com.etech.ha.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.login.bean.LoginForm;
import com.etech.ha.login.service.UserService;
import com.etech.ha.peer.UserPeer;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("user", new LoginForm());
		return mv;
	}
	/*
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(HttpServletRequest request, UserPeer user) {
		boolean rs = userService.checkLogin(user);
		if (rs) {
			request.getSession().setAttribute("user", user);
			return "main";
		} else {
			request.setAttribute("error", "用户名密码错误");
			return "login";
		}
	} 
	*/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, @ModelAttribute(value="user") @Valid LoginForm user, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("login", "error", "错误");
		}
		
		boolean rs = userService.checkLogin(user);
		if (rs) {
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		} else {
			return new ModelAndView("login", "error", "用户名密码错误");
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
