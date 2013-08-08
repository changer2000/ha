package com.etech.ha.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.login.bean.LoginForm;
import com.etech.ha.login.service.UserService;
import com.etech.ha.peer.UserPeer;
import com.etech.system.bean.UserInfo;
import com.etech.system.controller.BaseController;
import com.etech.validator.group.login.FirstGroup;

@Controller
public class LoginController extends BaseController {
	
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
	/*
	 * 方式1：
	 *    (1)@Valid LoginForm user
	 *    (2)public class LoginForm {
			 	......
			 	@NotEmpty
				@Length(message="{error.length}", min=4, max=50)
				private String empe_num;
			 	......
			 }
	 *    ---- 结果：NotEmpty和Length同时做验证
	 *    
	 * 方式2：
	 *    (1)@Valid LoginForm user
	 *    (2)@GroupSequence({LoginForm.class, FirstGroup.class})
			 public class LoginForm {
			 	......
			 	@NotEmpty
				@Length(message="{error.length}", min=4, max=50, groups=FirstGroup.class)
				private String empe_num;
			 	......
			 }
		  ----- 结果：先做NotEmpty验证，都通过，再做Length验证
	 * 
	 */
	//public ModelAndView doLogin(HttpServletRequest request, @ModelAttribute(value="user") @Valid LoginForm user, BindingResult result) {
	public ModelAndView doLogin(HttpServletRequest request, @ModelAttribute(value="user") @Valid LoginForm user, BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("login");
		}
		//ObjectError err = new ObjectError();
		//result.addError(arg0)
		if ("haha".equals(user.getEmpe_num())) {	//如果框架validator无法实现，可以在这里加入程序，自己实现。
			//result.rejectValue("empe_num", "reserved");
			result.rejectValue("empe_num", "reserved.data", new Object[] {user.getEmpe_num()}, null);
		}
		if (result.hasErrors()) {
			return new ModelAndView("login");
		}
		
		UserPeer userPeer = userService.checkLogin(user);
		if (userPeer!=null) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserPeer(userPeer);
			//TODO 将来可能还有其他的属性需要被设置
			
			request.getSession().setAttribute(HaConstants.SESSION_KEY_USER_INFO, userInfo);
			return new ModelAndView("main");
		} else {
			result.reject("error.login.user_pwd_error","login error");
			return new ModelAndView("login", result.getModel());
			//return new ModelAndView("login", "error", "用户名密码错误");
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
