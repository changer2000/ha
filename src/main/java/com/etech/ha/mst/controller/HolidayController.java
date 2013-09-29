package com.etech.ha.mst.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.service.HolidayService;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.bean.UserInfo;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("/maintain/holiday")
@SessionAttributes(value="SESSION_KEY_USER_INFO")
public class HolidayController extends BaseController {
	
	private static Log logger = LogFactory.getLog(HolidayController.class);
	
	@Autowired
	private HolidayService hldySvc;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		List<HolidayPeer> list = hldySvc.listAll();
		ModelAndView mv = new ModelAndView("hldyList");
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping(params="create", method=RequestMethod.POST)
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("hldyInfo");
		mv.addObject("command", new HolidayPeer());
		return mv;
	}
	
	@RequestMapping(params="modify", method=RequestMethod.POST)
	public ModelAndView modify(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, String[] id) {
		ModelAndView mv = new ModelAndView();
		
		if (id==null || id.length==0) {
			mv = list();
			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, "error.need.select.one", null)
				.setErrorsIntoModelView(mv);
			return mv;
		} else if (id.length>1) {
			mv = list();
			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, "error.cant.select.more.than.one", null)
				.setErrorsIntoModelView(mv);
			return mv;
		}
		
		HolidayPeer peer = hldySvc.findById(NumberUtils.createLong(id[0]));
		mv.addObject("command", peer);
		mv.setViewName("hldyInfo");
		return mv;
	}
	
	@RequestMapping(params="delete")
	public ModelAndView delete(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, String[] id) {
		ModelAndView mv = new ModelAndView();
		if (id==null || id.length==0) {
			mv = list();
			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, "error.need.select.one", null)
				.setErrorsIntoModelView(mv);
			return mv;
		}
		
		hldySvc.delete(id);
		return list();
	}
	
	@RequestMapping(params="register")
	public ModelAndView register(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, @Valid @ModelAttribute(value="command") HolidayPeer peer, BindingResult result) {
		ModelAndView mv = new ModelAndView("hldyInfo");
		if (!result.hasErrors()) {
			hldySvc.register(peer);
			MessagesBean msgBean = new MessagesBean();
			msgBean.addMessage(userInfo.getLocale(), messageSource, "msg.info.register.success", null)
				.setMessagesIntoModelView(mv);
		}
		return mv;
	}
	
	@RequestMapping(params="back", method=RequestMethod.POST)
	public ModelAndView back() {
		return list();
	}
	
	
	public HolidayService getHldySvc() {
		return hldySvc;
	}

	public void setHldySvc(HolidayService hldySvc) {
		this.hldySvc = hldySvc;
	}
	
}
