package com.pathfinder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pathfinder.director.CustomerDirector;
import com.pathfinder.model.CustomerPO;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDirector customerDirector;

	@RequestMapping(value = "addCustomer.htm", method = RequestMethod.GET)
	public ModelAndView addCustomer(
			@ModelAttribute("customer") CustomerPO customerPO,
			BindingResult result, HttpServletRequest httpServletRequest) {
		ModelAndView model = new ModelAndView();
		model.setViewName("customer/addCustomer");
		return model;
	}

	@RequestMapping(value = "saveCustomer.htm", method = RequestMethod.POST)
	public ModelAndView saveCustomer(
			@ModelAttribute("customer") CustomerPO customerPO,
			BindingResult result, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes) {
		customerPO.setUpdatedBy(httpServletRequest.getRemoteUser());
		customerDirector.saveCustomerDetails(customerPO);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:viewProperties.htm?customerid="
				+ customerPO.getCustomerID());
		redirectAttributes.addFlashAttribute("customer", customerPO);
		return model;
	}
	@RequestMapping(value = "viewCustomers.htm", method = RequestMethod.GET)
	public ModelAndView viewCustomers() {
		List<CustomerPO> customerPOs = customerDirector.viewCustomers();
		ModelAndView model = new ModelAndView();
		model.addObject("customerProperties", customerPOs);
		model.setViewName("customer/viewCustomers");
		return model;
	}

	@RequestMapping(value = "editCustomer.htm", method = RequestMethod.GET)
	public ModelAndView editCustomer(
			@RequestParam("customerid") String customerID,
			@ModelAttribute("customer") CustomerPO customerPO) {
		List<CustomerPO> customerPOs = customerDirector
				.getCustomerDetails(customerID);
		ModelAndView model = new ModelAndView();
		model.addObject("customer", customerPOs.get(0));
		model.setViewName("customer/editCustomer");
		return model;
	}

	@RequestMapping(value = "deleteCustomer.htm", method = RequestMethod.GET)
	public ModelAndView deleteCustomer(
			@RequestParam("customerid") String customerID) {
		customerDirector.deleteCustomer(customerID);
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:viewCustomers.htm");
		return model;
	}

}
