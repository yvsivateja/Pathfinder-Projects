package com.pathfinder.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PlotSafetyExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String exception(Exception e) {
		System.err.println("Technical Error : " + e.getMessage());
		return "technicalError";
	}
}
