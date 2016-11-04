package com.pathfinder.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pathfinder.delegate.GenerateReportDelegate;

@Controller
public class ReportsController {

	@Autowired
	private GenerateReportDelegate generateReportDelegate;

	@RequestMapping(value = "customerPropertyReport.htm", method = RequestMethod.GET)
	public void getCustomerReport(
			@RequestParam("customerid") String customerID,
			@RequestParam("identifier") String identifier,
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, SQLException, IOException {
		JasperPrint jasperPrint = generateReportDelegate
				.generatePDFReport(customerID);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=CustomerReport-" + identifier + ".pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		outStream.close();

	}
}
