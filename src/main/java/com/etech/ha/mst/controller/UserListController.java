package com.etech.ha.mst.controller;

import java.util.List;

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
import com.etech.ha.mst.form.PwdInfoForm;
import com.etech.ha.mst.form.UserListForm;
import com.etech.ha.mst.service.UserService;
import com.etech.ha.peer.UserPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.bean.UserInfo;
import com.etech.system.controller.BaseController;
import com.etech.system.utils.WebUtils;

@Controller
@RequestMapping("/maintain/user_list")
//@SessionAttributes("SESSION_KEY_USER_INFO")
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
	public ModelAndView show4Post(UserInfo userInfo) {
		UserListForm frm = new UserListForm();
		ModelAndView mv = errorHndl(userInfo, frm, null);
		
		UserListSearchBean searchBean = (UserListSearchBean) WebUtils.getSessionAttribute(userInfo, HaConstants.SUB_SYSTEM_MST, HaConstants.MODULE_USER_LIST, HaConstants.SESSION_KEY_USER_LIST_SEARCH_KEY);
		if (searchBean==null)
			searchBean = new UserListSearchBean();
		frm.setSearchBean(searchBean);
		mv.addObject("command", frm);
		
		return mv;
	}

	@RequestMapping(params="search", method=RequestMethod.POST)
	public ModelAndView search(UserInfo userInfo, @Valid @ModelAttribute("command") UserListForm frm, BindingResult result) {
		ModelAndView mv = new ModelAndView("userList");
		if (result.hasErrors())
			return mv;
		
		WebUtils.setSessionAttribute(userInfo, HaConstants.SUB_SYSTEM_MST, HaConstants.MODULE_USER_LIST, HaConstants.SESSION_KEY_USER_LIST_SEARCH_KEY, frm.getSearchBean());
		List<UserPeer> list = userSvc.search(userInfo, frm.getSearchBean());
		frm.setUserList(list);
		return mv;
	}
	
	@RequestMapping(params="create", method=RequestMethod.POST)
	public ModelAndView create(UserInfo userInfo, @ModelAttribute("command") UserListForm frm) {
		ModelAndView mv = new ModelAndView("redirect:user_info?empe_num=");
		return mv;
	}
	
	@RequestMapping(params="modify", method=RequestMethod.POST)
	public ModelAndView modify(UserInfo userInfo, @Valid @ModelAttribute("command") UserListForm frm, BindingResult result) {
		if (result.hasErrors()) {
			return errorHndl(userInfo, frm, null);
		}
		
		ModelAndView mv = null;	//userInfo
		String[] selKey = frm.getSelKey();
		if (selKey==null || selKey.length==0) {
			return errorHndl(userInfo, frm, "error.need.select.one");
		} else if (selKey.length>1) {
			return errorHndl(userInfo, frm, "error.cant.select.more.than.one");
		} else {
			UserPeer peer = userSvc.searchByEmpeNum(selKey[0]);
			if (peer!=null) {
				mv = new ModelAndView("redirect:user_info?empe_num="+peer.getEmpe_num());
			} else {
				return errorHndl(userInfo, frm, "error.data.is.not.exist");
			}
		}
		return mv;
	}

	@RequestMapping(params="delete", method=RequestMethod.POST)
	public ModelAndView delete(UserInfo userInfo, @Valid @ModelAttribute("command") UserListForm frm, BindingResult result) {
		if (result.hasErrors()) {
			return errorHndl(userInfo, frm, null);
		}
		
		ModelAndView mv = new ModelAndView("userInfo");
		
		String[] selKey = frm.getSelKey();
		if (selKey==null || selKey.length==0) {
			return errorHndl(userInfo, frm, "error.need.select.one");
		} else if (selKey.length>1) {
			return errorHndl(userInfo, frm, "error.cant.select.more.than.one");
		} else {
			userSvc.delete(frm.getSelKey());
			
			mv = search(userInfo, frm, result);
		}
		return mv;
	}
	
	@RequestMapping(params="resetPwd", method=RequestMethod.POST)
	public ModelAndView resetPwd(UserInfo userInfo, @ModelAttribute("command") UserListForm frm) {
		if (frm.getSelKey()==null || frm.getSelKey().length==0) {
			return errorHndl(userInfo, frm, "error.need.select.one");
		} else if (frm.getSelKey().length>1) {
			return errorHndl(userInfo, frm, "error.cant.select.more.than.one");
		} else {
			UserPeer peer = userSvc.searchByEmpeNum(frm.getSelKey()[0]);
			if (peer==null) {
				return errorHndl(userInfo, frm, "error.data.is.not.exist");
			}

			ModelAndView mv = new ModelAndView("forward:/maintain/pwd_info");
			//forward:/maintain/pwdreset?empe_num=test&empe_name=testname  只有这样才可以将数据以parameter方式传入下一个页面
			
			
			PwdInfoForm pwdFrm = new PwdInfoForm();
			pwdFrm.setEmpe_num(peer.getEmpe_num());
			pwdFrm.setEmpe_name(peer.getEmpe_name());
			WebUtils.setSessionAttribute(userInfo, HaConstants.SUB_SYSTEM_MST, 
					HaConstants.MODULE_PWD_RESET_INFO, HaConstants.FRM_PWDRESET,
					pwdFrm);
			
			return mv;
		}
	}
	
	public ModelAndView errorHndl(UserInfo userInfo, 
				UserListForm frm, String messageKey) {
		UserListSearchBean searchBean = (UserListSearchBean) WebUtils.getSessionAttribute(userInfo, HaConstants.SUB_SYSTEM_MST, HaConstants.MODULE_USER_LIST, HaConstants.SESSION_KEY_USER_LIST_SEARCH_KEY);
		if (searchBean==null)
			searchBean = new UserListSearchBean();
		
		List<UserPeer> list = userSvc.search(userInfo, searchBean);
		frm.setUserList(list);
		
		ModelAndView mv = new ModelAndView("userList");
		if (StringUtils.isNotBlank(messageKey)) {
			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, messageKey, null)
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
