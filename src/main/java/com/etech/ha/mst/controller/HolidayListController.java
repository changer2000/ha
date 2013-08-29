package com.etech.ha.mst.controller;

import java.util.List;

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
	public ModelAndView search(HolidayListForm frm) {
		ModelAndView mv = new ModelAndView("hldyListList");
		
		List<HolidayListPeer> list = hldyListSvc.listAll();
		frm.setList(list);
		mv.addObject("command", frm);
		
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
