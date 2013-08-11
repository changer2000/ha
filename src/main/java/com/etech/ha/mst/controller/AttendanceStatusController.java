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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.service.AttendanceStatusService;
import com.etech.ha.peer.AttendanceStatusPeer;
import com.etech.system.controller.BaseController;
import com.etech.system.utils.MessageUtils;

@Controller
@SessionAttributes(value="menu")
@RequestMapping("/maintain/adtnsSts")
public class AttendanceStatusController extends BaseController {
	
	private Log logger = LogFactory.getLog(AttendanceStatusController.class);
	
	@Autowired
	private AttendanceStatusService atndcStsSvc;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list() {
		List<AttendanceStatusPeer> list = atndcStsSvc.loadAll();
		ModelAndView mv = new ModelAndView("adtnsStsList");
		mv.addObject("adtnsStsList", list);
		return mv;
	}
	
	@RequestMapping(params="create", method=RequestMethod.POST)
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("command", new AttendanceStatusPeer());
		mv.setViewName("adtnsStsInfo");
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
		
		AttendanceStatusPeer peer = atndcStsSvc.findById(NumberUtils.createLong(id[0]));
		mv.addObject("command", peer);
		mv.setViewName("adtnsStsInfo");
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
		
		atndcStsSvc.delete(id);
		return list();
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute(value="command") AttendanceStatusPeer peer, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (!result.hasErrors())
			atndcStsSvc.reisger(peer);
		
		mv.setViewName("adtnsStsInfo");
		return mv;
	}
	
	@RequestMapping(params="back", method=RequestMethod.POST)
	public ModelAndView back() {
		return list();
	}

	public AttendanceStatusService getAtndcStsSvc() {
		return atndcStsSvc;
	}

	public void setAtndcStsSvc(AttendanceStatusService atndcStsSvc) {
		this.atndcStsSvc = atndcStsSvc;
	}
	
}
