package com.etech.ha.mst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.service.AttendanceStatusService;
import com.etech.ha.peer.AttendanceStatusPeer;
import com.etech.system.controller.BaseController;

@Controller
@SessionAttributes(value="menu")
@RequestMapping("/maintain/adtnsSts")
public class AttendanceStatusController extends BaseController {
	
	@Autowired
	private AttendanceStatusService atndcStsSvc;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView adtnsSts() {
		List<AttendanceStatusPeer> list = atndcStsSvc.loadAll();
		ModelAndView mv = new ModelAndView("adtnsStsList");
		mv.addObject("adtnsStsList", list);
		return mv;
	}

	public AttendanceStatusService getAtndcStsSvc() {
		return atndcStsSvc;
	}

	public void setAtndcStsSvc(AttendanceStatusService atndcStsSvc) {
		this.atndcStsSvc = atndcStsSvc;
	}
	
}
