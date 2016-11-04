package com.pathfinder.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pathfinder.util.UserAccessManagementUtil;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e) {

		ModelAndView mav = new ModelAndView("exception");
		// mav.addObject("name", e.getClass().getSimpleName());
		// mav.addObject("message", e.getMessage());
		System.err.println(e.getMessage());
		return mav;
	}

	@ExceptionHandler(BatasariWSException.class)
	@ResponseBody
	public String handleBatasariException(BatasariWSException e) {
		String json = "";
		try {
			json = "{ \"success\":false,"
					+ "\"message\":\""+e.getMessage()+"\""
					+ "}";
		} catch (Exception e1) {
		System.err.println("In Exception Handler : error while converting to json");
			json = "{ \"success\":false,"
					+ "\"message\":\"Server encounterd error please try after sometime \""
					+ "}";
		}
		return json;
	}

}
