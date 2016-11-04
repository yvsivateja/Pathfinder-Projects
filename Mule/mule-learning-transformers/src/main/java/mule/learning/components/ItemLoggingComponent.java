package mule.learning.components;

import mule.learning.beans.Item;

import org.mule.api.annotations.param.Payload;

public class ItemLoggingComponent {
	public void logComponent(@Payload Item item){
		System.out.println("item : "+item.getName());
		System.out.println("price : "+item.getPrice());
	}
}
