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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.mst.bean.UserListSearchBean;
import com.etech.ha.mst.form.UserListForm;
import com.etech.ha.mst.service.AttendanceStatusService;
import com.etech.ha.mst.service.UserService;
import com.etech.ha.peer.AttendanceStatusPeer;
import com.etech.ha.peer.UserPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.bean.UserInfo;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("maintain/user_info")
@SessionAttributes(value="SESSION_KEY_USER_INFO")
public class UserInfoContorller extends BaseController {
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private AttendanceStatusService atndcStsSvc;
	
	@Autowired
	private UserListController userListController;
	
	@ModelAttribute("atndcStsOptions")
	public List<AttendanceStatusPeer> optionsList(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo) {
		List<AttendanceStatusPeer> list = atndcStsSvc.loadAll();
		if (list!=null && list.size()>0) {
			String lblInShanghai = "option.in.shanghai";
			String lblNotInShanghai = "option.not.in.shanghai";
			for (AttendanceStatusPeer peer : list) {
				String lbl = lblInShanghai;
				if (peer.getIn_shanghai()!=null && 1==peer.getIn_shanghai().intValue()) {
					lbl = lblNotInShanghai;
				}
				peer.setAtndnc_name(peer.getAtndnc_name() + "-" + messageSource.getMessage(lbl, null, userInfo.getLocale()));
			}
		}
		
		return list;
	}
	
	@RequestMapping(method={RequestMethod.GET})
	public ModelAndView show(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, @RequestParam String empe_num) {
		if (isError(userInfo, empe_num)) {
			return new ModelAndView("redirect:/logout");
		} else {
			ModelAndView mv = new ModelAndView("userInfo");
			UserPeer peer = null;
			if (HaConstants.MODE_NEW.equals(userInfo.getSessionMap().get(HaConstants.SESSION_KEY_USER_INFO_MODE))) {
				peer = new UserPeer();
			} else {
				peer = userSvc.searchByEmpeNum(empe_num);
				peer.setDflt_atndnc_sts_id(peer.getAttendanceStatusPeer().getId());
			}
			mv.addObject("command", peer);
			return mv;
		}
	}
	
	@RequestMapping(params="back", method=RequestMethod.POST)
	public ModelAndView back(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo) {
		UserListForm frm = new UserListForm();
		ModelAndView mv = userListController.errorHndl(userInfo, frm, null);
		
		UserListSearchBean searchBean = (UserListSearchBean) userInfo.getSessionMap().get(HaConstants.SESSION_KEY_USER_LIST_SEARCH_KEY);
		if (searchBean==null)
			searchBean = new UserListSearchBean();
		frm.setSearchBean(searchBean);
		mv.addObject("command", frm);
		
		return mv;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, 
			@ModelAttribute("command") @Valid UserPeer peer,
			BindingResult result) {
		if (isError(userInfo, peer.getEmpe_num())) {
			return new ModelAndView("redirect:/logout");
		}
		if (result.hasErrors()) {
			return new ModelAndView("userInfo");
		}
		String mode = null;
		ModelAndView mv = new ModelAndView("userInfo");
		UserPeer dbPeer = userSvc.searchByEmpeNum(peer.getEmpe_num());
		if (dbPeer!=null && HaConstants.MODE_NEW.equals(mode)) {
			MessagesBean msgBean = new MessagesBean();
			msgBean.addMessage(userInfo.getLocale(), messageSource, "error.data.is.exist.already", null)
				.setMessagesIntoModelView(mv);
		} else  if (dbPeer==null && HaConstants.MODE_MODIFY.equals(mode)) {
			MessagesBean msgBean = new MessagesBean();
			msgBean.addMessage(userInfo.getLocale(), messageSource, "error.data.is.not.exist", null)
				.setMessagesIntoModelView(mv);
		} else {
			//prepare data
			peer.setAttendanceStatusPeer(atndcStsSvc.findById(peer.getDflt_atndnc_sts_id()));
			if (dbPeer!=null) {
				peer.setAdmin_flg(dbPeer.getAdmin_flg());
				peer.setDel_flg(dbPeer.getDel_flg());
				peer.setPwd(dbPeer.getPwd());
			}
			userSvc.register(peer);
			MessagesBean msgBean = new MessagesBean();
			msgBean.addMessage(userInfo.getLocale(), messageSource, "msg.info.register.success", null)
				.setMessagesIntoModelView(mv);
		}
		
		return mv;
	}
	
	private boolean isError(UserInfo userInfo, String empe_num) {
		String mode = (String) userInfo.getSessionMap().get(HaConstants.SESSION_KEY_USER_INFO_MODE);
		if (HaConstants.MODE_MODIFY.equals(mode)
				&& (
				StringUtils.isBlank(empe_num) || userInfo==null
				|| userInfo.getUserPeer().getAdmin_flg().intValue()!=1 && !empe_num.equals(userInfo.getUserPeer().getEmpe_num())
				)
			){
			return true;
		}
		return false;
	}

	public UserService getUserSvc() {
		return userSvc;
	}

	public void setUserSvc(UserService userSvc) {
		this.userSvc = userSvc;
	}

	public AttendanceStatusService getAtndcStsSvc() {
		return atndcStsSvc;
	}

	public void setAtndcStsSvc(AttendanceStatusService atndcStsSvc) {
		this.atndcStsSvc = atndcStsSvc;
	}

	public UserListController getUserListController() {
		return userListController;
	}

	public void setUserListController(UserListController userListController) {
		this.userListController = userListController;
	}
	
}
