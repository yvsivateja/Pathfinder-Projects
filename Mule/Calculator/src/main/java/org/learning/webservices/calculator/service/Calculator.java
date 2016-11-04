package org.learning.webservices.calculator.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.learning.webservices.calculator.beans.AdditionInput;
import org.learning.webservices.calculator.beans.DivisionInput;
import org.learning.webservices.calculator.beans.MultiplicationInput;
import org.learning.webservices.calculator.beans.Output;
import org.learning.webservices.calculator.beans.SubtractionInput;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface Calculator {
	
	@WebMethod(action="addNumbers")
	@WebResult(name = "response")
	public Output add(@WebParam(name = "additionRequest") AdditionInput input);
	
	@WebMethod(action="subtractNumbers")
	@WebResult(name = "response")
	public Output subtract(@WebParam(name = "subtractionRequest") SubtractionInput input);
	
	@WebMethod(action="multiplyNumbers")
	@WebResult(name = "response")
	public Output multiply(@WebParam(name = "multiplicationRequest") MultiplicationInput input);
	
	@WebMethod(action="divideNumbers")
	@WebResult(name = "response")
	public Output divide(@WebParam(name = "divisionRequest") DivisionInput input);
	
	
	
	
	
}
