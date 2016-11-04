package com.pathfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = "userLogin.htm", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error) {
		System.out.println("In Login");
		ModelAndView model = new ModelAndView();
		model.setViewName("userLogin");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("userLoginError");
		}
		return model;
	}

}
