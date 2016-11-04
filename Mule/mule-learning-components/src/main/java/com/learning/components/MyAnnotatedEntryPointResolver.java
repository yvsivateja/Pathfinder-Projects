package com.learning.components;

import org.mule.api.MuleContext;
import org.mule.api.annotations.expression.Groovy;
import org.mule.api.annotations.expressions.Function;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.annotations.param.InboundHeaders;
import org.mule.api.annotations.param.Payload;

import com.learning.beans.Address;
import com.learning.beans.Person;

public class MyAnnotatedEntryPointResolver {
	
	@Lookup("address") Address address;
	@Lookup MuleContext muleContext;
	
	public String access(@Payload Person person,@Function("dateStamp-yyyy-MM-dd") String date,
			@Groovy("payload.name") String name, @InboundHeaders("content-type")String contentType, @Lookup("address") Address address,@Lookup MuleContext muleContext){
		System.out.println("Name :"+person.getName());
		System.out.println("Age :"+person.getAge());
		System.out.println(date);
		System.out.println(name);
		System.out.println("This is the content type:"+contentType);
		System.out.println(address);
		System.out.println(this.address);//null
		System.out.println(muleContext);
		System.out.println(this.muleContext);//null
		return name;
	}
}
