package mule.learning.components;

import mule.learning.beans.Laptop;

import org.mule.api.annotations.param.Payload;

public class LaptopLoggingComponent {
	public void logComponent(@Payload Laptop laptop){
		System.out.println("Model : "+laptop.getModel());
		System.out.println("Size : "+laptop.getSize());
	}
}
