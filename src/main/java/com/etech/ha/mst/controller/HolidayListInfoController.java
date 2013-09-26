package com.etech.ha.mst.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
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
			//logic validate date
			validateRegister(request, listPeer, result);
			
			if (!result.hasErrors()) {
				//prepare peer
				listPeer.setHolidayPeer(new HolidayPeer());
				listPeer.getHolidayPeer().setId(listPeer.getHolidayPeerId());
				if (listPeer.getHldyListDtlList()!=null) {
					for (HolidayListDtlPeer dtlPeer : listPeer.getHldyListDtlList()) {
						dtlPeer.setHldyListPeer(listPeer);
					}
				}
				hldyListSvc.register(listPeer);
	
				MessagesBean msgBean = new MessagesBean();
				msgBean.addMessage(request, messageSource, "msg.info.register.success", null)
					.setMessagesIntoModelView(mv);
			}
		}
		return mv;
	}
	
	private MessagesBean validateRegister(HttpServletRequest request, HolidayListPeer listPeer, BindingResult result) {
		MessagesBean msgBean = new MessagesBean();
		if (listPeer.getStart_dt().compareTo(listPeer.getEnd_dt())>0) {
			result.rejectValue("start_dt", "error.date.relation");
		} else {
			if (listPeer.getHldyListDtlList()!=null && listPeer.getHldyListDtlList().size()>0) {
				int i=0;
				for (HolidayListDtlPeer dtlPeer : listPeer.getHldyListDtlList()) {
					if (dtlPeer.getHldy_start().compareTo(dtlPeer.getHldy_end())>0) {
						result.rejectValue("hldyListDtlList["+i+"].hldy_start", "error.date.relation");
					} else {
						if (dtlPeer.getHldy_start().compareTo(listPeer.getStart_dt())<0) {
							result.rejectValue("hldyListDtlList["+i+"].hldy_start", "error.holiday.start.date.with.start.date");
						}
						if (dtlPeer.getHldy_end().compareTo(listPeer.getEnd_dt())>0) {
							result.rejectValue("hldyListDtlList["+i+"].hldy_end", "error.holiday.end.date.with.end.date");
						}
					}
					i++;
				}
				
				Map<Date, Date> duplDtCheck = new HashMap<Date, Date>();
				i=0;
				for (HolidayListDtlPeer dtlPeer : listPeer.getHldyListDtlList()) {
					Date startDt = dtlPeer.getHldy_start();
					while (startDt.compareTo(dtlPeer.getHldy_end())<=0) {
						if (duplDtCheck.get(startDt)==null) {
							duplDtCheck.put(startDt, startDt);
						} else {
							result.rejectValue("hldyListDtlList["+i+"].hldy_start", "error.holiday.doublication");
							break;
						}
						
						startDt = DateUtils.add(startDt, Calendar.DAY_OF_YEAR, 1);
					}
					i++;
				}
			}
		}
		if (msgBean.hasErrors())
			return msgBean;
		
		return null;
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
