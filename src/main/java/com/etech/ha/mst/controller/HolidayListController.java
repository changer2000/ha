package com.etech.ha.mst.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.mst.form.HolidayListForm;
import com.etech.ha.mst.service.HolidayListService;
import com.etech.ha.mst.service.HolidayService;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.bean.UserInfo;
import com.etech.system.controller.BaseController;

@Controller
@SessionAttributes(value="SESSION_KEY_USER_INFO")
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
	public ModelAndView search(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, @ModelAttribute("command") HolidayListForm frm) {
		ModelAndView mv = new ModelAndView("hldyListList");
		userInfo.getSessionMap().put(HaConstants.SESSION_KEY_HOLIDAY_LIST_SEARCH_KEY, frm.getSearchBean());
		
		List<HolidayListPeer> list = hldyListSvc.search(frm.getSearchBean());
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
	public ModelAndView modify(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, @ModelAttribute("command") HolidayListForm frm) {
		ModelAndView mv = null;
		
		String[] selKey = frm.getSelKey();
		if (selKey==null || selKey.length==0) {
			mv = search(userInfo, frm);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, "error.need.select.one", null)
				.setErrorsIntoModelView(mv);
		} else if (selKey.length>1) {
			mv = search(userInfo, frm);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, "error.cant.select.more.than.one", null)
				.setErrorsIntoModelView(mv);
		} else {
			HolidayListPeer listPeer = hldyListSvc.findById(NumberUtils.createLong(selKey[0]));
			listPeer.setHolidayPeerId(listPeer.getHolidayPeer().getId());
						
			mv = new ModelAndView("hldyListInfo");
			mv.addObject("command", listPeer);
		}
		
		return mv;
	}
	
	@RequestMapping(params="delete", method=RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("SESSION_KEY_USER_INFO") UserInfo userInfo, @ModelAttribute("command") HolidayListForm frm) {
		ModelAndView mv = null;
		if (frm.getSelKey()==null || frm.getSelKey().length==0) {
			mv = search(userInfo, frm);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addError(userInfo.getLocale(), messageSource, "error.need.select.one", null)
				.setErrorsIntoModelView(mv);
			
		} else {
			hldyListSvc.delete(frm.getSelKey());
			
			mv = search(userInfo, frm);
		}
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
