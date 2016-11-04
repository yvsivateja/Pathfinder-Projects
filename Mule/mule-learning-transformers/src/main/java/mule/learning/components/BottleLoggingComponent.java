package mule.learning.components;

import mule.learning.beans.Bottle;

import org.mule.api.annotations.param.Payload;

public class BottleLoggingComponent {
	public void logBottle(@Payload Bottle bottle){
		System.out.println("Brand : "+bottle.getBrand());
		System.out.println("Type : "+bottle.getType());
	}
}
