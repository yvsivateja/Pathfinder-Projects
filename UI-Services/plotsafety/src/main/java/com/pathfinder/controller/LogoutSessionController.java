package com.pathfinder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutSessionController {

	@RequestMapping(value = "logout.htm", method = RequestMethod.GET)
	public String onSubmit(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/";
	}
}
