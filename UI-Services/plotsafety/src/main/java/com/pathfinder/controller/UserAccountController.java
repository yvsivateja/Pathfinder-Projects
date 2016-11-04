package com.pathfinder.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pathfinder.director.UserAccountDirector;
import com.pathfinder.model.User;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountDirector accountDirector;

	@RequestMapping(value = "changePassword.htm", method = RequestMethod.GET)
	public ModelAndView changeUserPassword() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("changePassword");
		modelAndView.addObject("status", false);
		return modelAndView;
	}

	@RequestMapping(value = "updatePassword.htm", method = RequestMethod.POST)
	public ModelAndView updatePassword(@RequestParam String newPassword,
			HttpServletRequest httpServletRequest) {
		accountDirector.changePassword(newPassword,
				httpServletRequest.getRemoteUser());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("changePassword");
		modelAndView.addObject("status", true);
		return modelAndView;
	}

	@RequestMapping(value = "addUser.htm", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user,
			HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adduser");
		return modelAndView;
	}

	@RequestMapping(value = "saveUser.htm", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user,
			HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("adduser");
		return modelAndView;
	}
}
