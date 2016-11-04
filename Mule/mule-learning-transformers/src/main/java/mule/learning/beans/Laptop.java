package mule.learning.beans;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Laptop {
	
	private String model;
	private String size;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
}
