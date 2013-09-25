package com.etech.ha.mst.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.constants.HaConstants;
import com.etech.ha.mst.bean.HolidayListForm;
import com.etech.ha.mst.bean.HolidayListSearchBean;
import com.etech.ha.mst.service.HolidayListService;
import com.etech.ha.mst.service.HolidayService;
import com.etech.ha.peer.HolidayListDtlPeer;
import com.etech.ha.peer.HolidayListPeer;
import com.etech.ha.peer.HolidayPeer;
import com.etech.system.bean.MessagesBean;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("/maintain/holiday_info")
public class HolidayListInfoController extends BaseController {
	
	@Autowired
	private HolidayService hldySvc;
	
	@Autowired
	private HolidayListService hldyListSvc;
	
	@Autowired
	private HolidayListController hldyListController;
	
	@ModelAttribute("hldyOptions")
	public List<HolidayPeer> initHldyOptions() {
		return hldyListController.initHldyOptions();
	}
	
	@RequestMapping(params="back", method=RequestMethod.POST)
	public ModelAndView back(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("hldyListList");
		HolidayListSearchBean searchBean = (HolidayListSearchBean)
				request.getSession().getAttribute(HaConstants.SESSION_KEY_HOLIDAY_LIST_SEARCH_KEY);
		
		List<HolidayListPeer> list = hldyListSvc.search(searchBean);
		HolidayListForm frm = new HolidayListForm();
		frm.setList(list);
		mv.addObject("command", frm);
		return mv;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, @Valid @ModelAttribute(value="command") HolidayListPeer listPeer, BindingResult result) {
		ModelAndView mv = new ModelAndView("hldyListInfo");
		if (!result.hasErrors()) {
			//prepare peer
			listPeer.setHolidayPeer(new HolidayPeer());
			listPeer.getHolidayPeer().setId(listPeer.getHolidayPeerId());
			for (HolidayListDtlPeer dtlPeer : listPeer.getHldyListDtlList()) {
				dtlPeer.setHldyListPeer(listPeer);
			}
			hldyListSvc.register(listPeer);

			MessagesBean msgBean = new MessagesBean();
			msgBean.addMessage(request, messageSource, "msg.info.register.success", null)
				.setMessagesIntoModelView(mv);
		}
		return mv;
	}
	

	public HolidayService getHldySvc() {
		return hldySvc;
	}

	public void setHldySvc(HolidayService hldySvc) {
		this.hldySvc = hldySvc;
	}

	public HolidayListController getHldyListController() {
		return hldyListController;
	}

	public void setHldyListController(HolidayListController hldyListController) {
		this.hldyListController = hldyListController;
	}

	public HolidayListService getHldyListSvc() {
		return hldyListSvc;
	}

	public void setHldyListSvc(HolidayListService hldyListSvc) {
		this.hldyListSvc = hldyListSvc;
	}
	
}
