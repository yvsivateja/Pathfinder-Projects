package org.learning.webservices.calculator.beans;

import java.io.Serializable;

public class MultiplicationInput implements Serializable {

	private static final long serialVersionUID = -7255288066786909508L;
	
	private int first;
	
	private int second;
	
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	

}
