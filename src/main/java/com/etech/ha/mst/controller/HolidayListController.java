package com.etech.ha.mst.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.bean.HolidayListForm;
import com.etech.ha.mst.service.HolidayListService;
import com.etech.ha.mst.service.HolidayService;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("/maintain/holiday_list")
public class HolidayListController extends BaseController {
	
	@Autowired
	private HolidayListService hldyListSvc;
	
	@Autowired
	private HolidayService hldySvc;
	
	
	public Map<Long, String> initHldyOptions() {
		Map<Long, String> mapping = new LinkedHashMap<Long, String>();
		
		List<HolidayPeer> list = hldySvc.listAll();
		if (list!=null && list.size()>0) {
			for (HolidayPeer peer : list) {
				mapping.put(peer.getId(), peer.getName());
			}
		}
		
		return mapping;
	}
	
	public List<HolidayPeer> initHldyOptions2() {
		
		List<HolidayPeer> list = hldySvc.listAll();
		return list;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		HolidayListForm frm = new HolidayListForm();
		//frm.setHldyOptions(initHldyOptions2());
		mv.addObject("hldyOptions", initHldyOptions2());
		mv.addObject("command", frm);
		mv.setViewName("hldyListList");
		
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
