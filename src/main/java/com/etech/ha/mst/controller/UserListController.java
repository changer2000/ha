package com.etech.ha.mst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.mst.bean.UserListSearchBean;
import com.etech.ha.mst.form.UserListForm;
import com.etech.ha.mst.service.UserService;
import com.etech.ha.peer.UserPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("/maintain/user_list")
public class UserListController extends BaseController {
	
	@Autowired
	private UserService userSvc;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView("userList");
		UserListForm frm = new UserListForm();
		mv.addObject("command", frm);
		return mv;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, @Valid @ModelAttribute("command") UserListForm frm, BindingResult result) {
		ModelAndView mv = new ModelAndView("userList");
		if (result.hasErrors())
			return mv;
		
		request.getSession().setAttribute(HaConstants.SESSION_KEY_USER_LIST_SEARCH_KEY, frm.getSearchBean());
		
		List<UserPeer> list = userSvc.search(frm.getSearchBean());
		frm.setUserList(list);
		return mv;
	}
	
	@RequestMapping(params="create", method=RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, @ModelAttribute("command") UserListForm frm) {
		ModelAndView mv = new ModelAndView("userInfo");
		request.getSession().setAttribute(HaConstants.SESSION_KEY_USER_INFO_MODE, HaConstants.MODE_NEW);
		mv.addObject("command", new UserPeer());
		return mv;
	}
	
	@RequestMapping(params="modify", method=RequestMethod.POST)
	public ModelAndView modify(HttpServletRequest request, @Valid @ModelAttribute("command") UserListForm frm, BindingResult result) {
		ModelAndView mv = new ModelAndView("userInfo");
		if (result.hasErrors()) {
			return errorHndl(request, frm, null);
		}
		
		String[] selKey = frm.getSelKey();
		if (selKey==null || selKey.length==0) {
			mv = errorHndl(request, frm, "error.need.select.one");
		} else if (selKey.length>1) {
			mv = errorHndl(request, frm, "error.cant.select.more.than.one");
		} else {
			UserPeer peer = userSvc.searchByEmpeNum(selKey[0]);
			if (peer!=null) {
				request.getSession().setAttribute(HaConstants.SESSION_KEY_USER_INFO_MODE, HaConstants.MODE_MODIFY);
				mv.addObject("command", peer);
			} else {
				mv = errorHndl(request, frm, "error.data.is.not.exist");
			}
		}
		return mv;
	}

	@RequestMapping(params="delete", method=RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, @Valid @ModelAttribute("command") UserListForm frm, BindingResult result) {
		if (result.hasErrors()) {
			return errorHndl(request, frm, null);
		}
		
		ModelAndView mv = new ModelAndView("userInfo");
		
		String[] selKey = frm.getSelKey();
		if (selKey==null || selKey.length==0) {
			mv = errorHndl(request, frm, "error.need.select.one");
		} else if (selKey.length>1) {
			mv = errorHndl(request, frm, "error.cant.select.more.than.one");
		} else {
			userSvc.delete(frm.getSelKey());
			
			mv = search(request, frm, result);
		}
		return mv;
	}
	
	private ModelAndView errorHndl(HttpServletRequest request, 
				UserListForm frm, String messageKey) {
		UserListSearchBean searchBean = (UserListSearchBean) request.getSession().getAttribute(HaConstants.SESSION_KEY_USER_LIST_SEARCH_KEY);
		if (searchBean==null)
			searchBean = new UserListSearchBean();
		
		List<UserPeer> list = userSvc.search(searchBean);
		frm.setUserList(list);
		
		ModelAndView mv = new ModelAndView("userList");
		if (StringUtils.isNotBlank(messageKey)) {
			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(request, messageSource, messageKey, null)
				.setErrorsIntoModelView(mv);
		}
		return mv;
	}
	
	public UserService getUserSvc() {
		return userSvc;
	}

	public void setUserSvc(UserService userSvc) {
		this.userSvc = userSvc;
	}
	
}
