package mule.learning.components;


import javax.inject.Inject;

import mule.learning.beans.Address;
import mule.learning.beans.Consultant;
import mule.learning.beans.Person;

import org.mule.api.MuleContext;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.annotations.param.Payload;

public class ConsultantLoggingComponent {
	/*
	 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
	 * Because, there is a registry change happened in mule 3.7 which enables this.
	 */
	@Inject
	private MuleContext injectedContext;
	@Inject
	private Address injectedAddress;
	//These are resolving to null in mule 3.7, but these are resolved to proper value in mule 3.6
	@Lookup
	private MuleContext context;
	@Lookup
	private Address address;
	
	public Consultant logManager(@Payload Consultant consultant, @Lookup MuleContext context, @Lookup("address") Address address){
		Person person = consultant.getPerson();
		System.out.println("Name: "+person.getName());
		System.out.println("Age: "+person.getAge());
		System.out.println("Designation: "+consultant.getDesignation());
		
		System.out.println("Lookup - component - param level : "+context);
		System.out.println("Lookup - component - param level : "+address);
		
		//These are resolving to null in mule 3.7, but these are resolved to proper value in mule 3.6
		System.out.println("Lookup - component - instance level : "+this.context);
		System.out.println("Lookup - component - instance level : "+this.address);
		
		/*
		 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
		 * Because, there is a registry change happened in mule 3.7 which enables this.
		 */
		System.out.println("inject - component : "+injectedContext);
		System.out.println("inject - component : "+injectedAddress);
		
		return consultant;
	}
}
