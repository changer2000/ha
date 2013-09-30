package com.etech.ha.mst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.etech.ha.mst.form.UserInfoForm;
import com.etech.system.controller.BaseController;

@Controller
@RequestMapping("maintain/pwdreset")
@SessionAttributes(value="SESSION_KEY_USER_INFO")
public class PwdInfoController extends BaseController {
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView show(@ModelAttribute("command") UserInfoForm form) {
		System.out.println(">>>>>>" + form.getEmpe_name());
		
		return null;
	}
	
}
