package com.pathfinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pathfinder.director.PropertyDirector;
import com.pathfinder.model.CustomerPO;
import com.pathfinder.model.PropertyPO;

@Controller
public class PropertyController {

	@Autowired
	private PropertyDirector propertyDirector;

	@ModelAttribute("property")
	public PropertyPO propertyModel() {
		return new PropertyPO();
	}

	@RequestMapping(value = "viewProperties.htm", method = RequestMethod.GET)
	public ModelAndView viewProperties(
			@RequestParam("customerid") String customerID,
			@ModelAttribute("customer") CustomerPO customerPO) {
		ModelAndView model = new ModelAndView();
		model.setViewName("property/viewProperty");

		model.addObject("customerProperties",
				propertyDirector.getCustomerProperties(customerID));
		return model;
	}

	@RequestMapping(value = "addProperty.htm", method = RequestMethod.POST)
	public ModelAndView addProperty(
			@ModelAttribute("property") PropertyPO propertyPO) {
		propertyDirector.addProperty(propertyPO);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:viewProperties.htm?customerid="
				+ propertyPO.getCustomerID());

		return model;
	}

	@RequestMapping(value = "editProperty.htm", method = RequestMethod.GET)
	public ModelAndView getProperty(
			@RequestParam("propertyid") String propertiesID) {
		PropertyPO propertyPO = propertyDirector
				.getPropertyDetails(propertiesID);
		ModelAndView model = new ModelAndView();
		model.addObject("property", propertyPO);
		model.setViewName("property/editProperty");

		return model;
	}

	@RequestMapping(value = "deleteProperty.htm", method = RequestMethod.GET)
	public ModelAndView deleteCustomer(
			@RequestParam("customerid") String customerID,
			@RequestParam("propertyid") String propertyID) {
		propertyDirector.deleteProperty(propertyID);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:viewProperties.htm?customerid="
				+ customerID);
		return model;
	}
}
