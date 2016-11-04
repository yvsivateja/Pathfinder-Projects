package org.learning.webservices.calculator.beans;

import java.io.Serializable;

public class Output implements Serializable {

	private static final long serialVersionUID = -5664298446287952010L;
	
	private double result;

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
	

}
