package com.pathfinder.delegate;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class GenerateReportDelegate {

	@Autowired
	private DriverManagerDataSource dataSource;

	@Autowired
	private ServletContext servletContext;

	private static final String REPORT_FILE = "/reports/Customer_PropertyListReport.jrxml";

	public JasperPrint generatePDFReport(String customerID) throws JRException,
			SQLException {
		InputStream jasperStream = this.getClass().getResourceAsStream(
				REPORT_FILE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("CUSTOMER_ID", customerID);
		params.put("CONTEXTPATH", servletContext.getRealPath("/"));
		JasperReport jasperReport = JasperCompileManager
				.compileReport(jasperStream);
		return JasperFillManager.fillReport(jasperReport, params,
				dataSource.getConnection());

	}
}
