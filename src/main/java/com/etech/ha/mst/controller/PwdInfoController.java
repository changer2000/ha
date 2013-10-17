package com.etech.ha.mst.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.mst.form.PwdInfoForm;
import com.etech.ha.mst.service.UserService;
import com.etech.ha.peer.UserPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.bean.UserInfo;
import com.etech.system.controller.BaseController;
import com.etech.system.utils.WebUtils;

@Controller
@RequestMapping("maintain/pwd_info")
//@SessionAttributes(value="SESSION_KEY_USER_INFO")
public class PwdInfoController extends BaseController {
	
	@Autowired
	private UserService userSvc;
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView show(UserInfo userInfo) {
		PwdInfoForm form = (PwdInfoForm) WebUtils.getSessionAttribute(userInfo, 
				HaConstants.SUB_SYSTEM_MST, HaConstants.MODULE_PWD_RESET_INFO,
				HaConstants.FRM_PWDRESET);
		WebUtils.removeSessionAttribute(userInfo, HaConstants.SUB_SYSTEM_MST, HaConstants.MODULE_PWD_RESET_INFO, HaConstants.FRM_PWDRESET);
		
		ModelAndView mv = new ModelAndView("pwdInfo");
		mv.addObject("command", form);
		return mv;
	}
	
	@RequestMapping(params="back", method=RequestMethod.POST)
	public ModelAndView back() {
		ModelAndView mv = new ModelAndView("forward:/maintain/user_list");
		return mv;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public ModelAndView register(UserInfo userInfo,
			@ModelAttribute("command") @Valid PwdInfoForm frm, BindingResult result) {
		ModelAndView mv = new ModelAndView("pwdInfo");
		if (result.hasErrors())
			return mv;
		
		UserPeer peer = userSvc.searchByEmpeNum(frm.getEmpe_num());
		if (peer==null) {
			errorHndl(userInfo, mv, "error.data.is.not.exist");
		} else {
			if (frm.getOld_pwd().equals(peer.getPwd())) {
				if (frm.getNew_pwd()!=null && frm.getNew_pwd_again()!=null
						&& frm.getNew_pwd().equals(frm.getNew_pwd_again())) {
					userSvc.changePwd(frm);
					MessagesBean msgBean = new MessagesBean();
					msgBean.addMessage(userInfo.getLocale(), messageSource, "msg.info.register.success", null)
						.setMessagesIntoModelView(mv);
				} else {
					errorHndl(userInfo, mv, "error.new.password.not.equal");
				}
			} else {
				errorHndl(userInfo, mv, "error.not.equal.old.password");
			}
		}
		return mv;
	}
	
	private void errorHndl(UserInfo userInfo, ModelAndView mv, String message) {
		MessagesBean msgBean = new MessagesBean();
		msgBean.addError(userInfo.getLocale(), messageSource, message, null)
			.setErrorsIntoModelView(mv);
	}

	public UserService getUserSvc() {
		return userSvc;
	}

	public void setUserSvc(UserService userSvc) {
		this.userSvc = userSvc;
	}
	
}
