package com.learning.validators;

import org.mule.api.MuleEvent;
import org.mule.extension.validation.api.ValidationResult;
import org.mule.extension.validation.api.Validator;

public class VariableValidator implements Validator {

	@Override
	public ValidationResult validate(final MuleEvent event) {
		ValidationResult result = new ValidationResult(){

			@Override
			public String getMessage() {
				return "Flowvariable name is invalid";
			}

			@Override
			public boolean isError() {
				return !((String)event.getFlowVariable("email")).contains("sai");
			}
			
		};
		return result;
	}

}
