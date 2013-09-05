package com.etech.ha.mst.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.bean.HolidayListForm;
import com.etech.ha.mst.service.HolidayListService;
import com.etech.ha.mst.service.HolidayService;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("/maintain/holiday_list")
public class HolidayListController extends BaseController {
	
	@Autowired
	private HolidayListService hldyListSvc;
	
	@Autowired
	private HolidayService hldySvc;
	
	@ModelAttribute("hldyOptions")
	public List<HolidayPeer> initHldyOptions() {
		List<HolidayPeer> list = hldySvc.listAll();
		if (list==null) {
			list = new ArrayList<HolidayPeer>();
		}
		list.add(0, new HolidayPeer());
		return list;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		HolidayListForm frm = new HolidayListForm();
		mv.addObject("command", frm);
		mv.setViewName("hldyListList");
		return mv;
	}
	
	@RequestMapping(params="search", method=RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("command") HolidayListForm frm) {
		ModelAndView mv = new ModelAndView("hldyListList");
		
		List<HolidayListPeer> list = hldyListSvc.listAll();
		frm.setList(list);
		//mv.addObject("command", frm);
		
		return mv;
	}
	
	@RequestMapping(params="create", method=RequestMethod.POST) 
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hldyListInfo");
		mv.addObject("command", new HolidayListPeer());
		return mv;
	}
	
	@RequestMapping(params="modify", method=RequestMethod.POST)
	public ModelAndView modify(HttpServletRequest request, HolidayListForm frm) {
		ModelAndView mv = null;
		
		String[] selKey = frm.getSelKey();
		if (selKey==null || selKey.length==0) {
			mv = search(frm);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(request, messageSource, "error.need.select.one", null)
				.setErrorsIntoModelView(mv);
		} else if (selKey.length>1) {
			mv = search(frm);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(request, messageSource, "error.cant.select.more.than.one", null)
				.setErrorsIntoModelView(mv);
		} else {
			HolidayListPeer listPeer = hldyListSvc.findById(NumberUtils.createLong(selKey[0]));
			mv = new ModelAndView("hldyListInfo");
			mv.addObject("command", listPeer);
		}
		
		return mv;
	}
	
	@RequestMapping(params="delete", method=RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, @ModelAttribute("command") HolidayListForm frm) {
		ModelAndView mv = null;
		if (frm.getSelKey()==null || frm.getSelKey().length==0) {
			mv = search(frm);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(request, messageSource, "error.need.select.one", null)
				.setErrorsIntoModelView(mv);
			
		} else {
			hldyListSvc.delete(frm.getSelKey());
			
			mv = search(frm);
		}
		return mv;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("command") HolidayListPeer peer) {
		ModelAndView mv = new ModelAndView("hldyListInfo");
		hldyListSvc.register(peer);
		return mv;
	}
	

	public HolidayListService getHldyListSvc() {
		return hldyListSvc;
	}

	public void setHldyListSvc(HolidayListService hldyListSvc) {
		this.hldyListSvc = hldyListSvc;
	}

	public HolidayService getHldySvc() {
		return hldySvc;
	}

	public void setHldySvc(HolidayService hldySvc) {
		this.hldySvc = hldySvc;
	}
	
}