package org.learning.webservices.calculator.service;

import javax.jws.WebService;

import org.learning.webservices.calculator.beans.AdditionInput;
import org.learning.webservices.calculator.beans.DivisionInput;
import org.learning.webservices.calculator.beans.MultiplicationInput;
import org.learning.webservices.calculator.beans.Output;
import org.learning.webservices.calculator.beans.SubtractionInput;

@WebService(portName = "CalculatorPort", serviceName = "Calculator",
endpointInterface = "org.learning.webservices.calculator.service.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public Output add(AdditionInput input) {
		Output output = new Output();
		output.setResult(input.getFirst()+input.getSecond());
		return output;
	}

	@Override
	public Output subtract(SubtractionInput input) {
		Output output = new Output();
		output.setResult(input.getFirst()-input.getSecond());
		return output;
	}

	@Override
	public Output multiply(MultiplicationInput input) {
		Output output = new Output();
		output.setResult(input.getFirst()*input.getSecond());
		return output;
	}

	@Override
	public Output divide(DivisionInput input) {
		Output output = new Output();
		output.setResult(input.getFirst()/input.getSecond());
		return output;
	}

}
