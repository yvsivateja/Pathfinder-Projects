package com.pathfinder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pathfinder.constants.RealConstants;

/**
 * @author Siva Teja
 * 
 */
@Controller
@RequestMapping("/logout.htm")
public class LogOutSessionController {

	@RequestMapping(method = RequestMethod.GET)
	public String onSubmit(HttpSession session, HttpServletRequest request) {
		request.getSession().setAttribute("sessionUserDetails", null);
		session.invalidate();
		return "redirect:" + RealConstants.GROUPS_ALL_VIEW;
	}
}
