package com.etech.system.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	public @ResponseBody String handleBusinessException(Exception ex) {
		
		return "Haha!\r\n" + ex.getMessage();
	}
	
	@ExceptionHandler
	public ModelAndView handleException(Exception ex) {
		ModelAndView mv = new ModelAndView("common/error");
		ExceptionMsgBean errMsgBean = new ExceptionMsgBean();
		errMsgBean.setEx(ex);
		mv.addObject("errMsgBean", errMsgBean);
		return mv;
	}
	
}
