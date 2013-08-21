package com.etech.ha.mst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.service.HolidayService;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.controller.BaseController;
import com.etech.system.utils.MessageUtils;

@Controller
@RequestMapping("/maintain/holiday")
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
	public ModelAndView modify(HttpServletRequest request, String[] id) {
		ModelAndView mv = new ModelAndView();
		
		if (id==null || id.length==0) {
			mv = list();
			MessageUtils.getMessagesBean()
				.addMessage(request, messageSource, "error.need.select.one", null)
				.setIntoModelView(mv);
			return mv;
		} else if (id.length>1) {
			mv = list();
			MessageUtils.getMessagesBean()
				.addMessage(request, messageSource, "error.cant.select.more.than.one", null)
				.setIntoModelView(mv);
			return mv;
		}
		
		HolidayPeer peer = hldySvc.findById(NumberUtils.createLong(id[0]));
		mv.addObject("command", peer);
		mv.setViewName("hldyInfo");
		return mv;
	}
	
	@RequestMapping(params="delete")
	public ModelAndView delete(HttpServletRequest request, String[] id) {
		ModelAndView mv = new ModelAndView();
		if (id==null || id.length==0) {
			mv = list();
			MessageUtils.getMessagesBean()
				.addMessage(request, messageSource, "error.need.select.one", null)
				.setIntoModelView(mv);
			return mv;
		}
		
		hldySvc.delete(id);
		return list();
	}
	
	@RequestMapping(params="register")
	public ModelAndView register(@Valid @ModelAttribute(value="command") HolidayPeer peer, BindingResult result) {
		ModelAndView mv = new ModelAndView("hldyInfo");
		if (!result.hasErrors())
			hldySvc.register(peer);
		
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
