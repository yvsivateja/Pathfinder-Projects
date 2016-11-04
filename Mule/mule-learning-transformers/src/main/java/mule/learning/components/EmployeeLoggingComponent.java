package mule.learning.components;


import javax.inject.Inject;

import mule.learning.beans.Address;
import mule.learning.beans.Consultant;
import mule.learning.beans.Employee;
import mule.learning.beans.Manager;
import mule.learning.beans.Person;

import org.mule.api.MuleContext;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.annotations.param.Payload;
/*
 * This class is meant to register from registry-bootstrap.properties
 */
public class EmployeeLoggingComponent {
	
	@Inject
	private MuleContext injectedContext;
	@Inject
	private Address injectedAddress;
	//These are resolving to null
	@Lookup
	private MuleContext context;
	@Lookup
	private Address address;
	
	public Employee logManager(@Payload Employee employee, @Lookup MuleContext context, @Lookup("address") Address address){
		Consultant consultant = employee.getConsultant();
		System.out.println("===========Consultant========");
		Person person = consultant.getPerson();
		System.out.println("Name: "+person.getName());
		System.out.println("Age: "+person.getAge());
		System.out.println("Designation: "+consultant.getDesignation());
		System.out.println("===========Consultant========");
		System.out.println("===========Manager========");
		Manager manager = employee.getManager();
		person = manager.getPerson();
		System.out.println("Name: "+person.getName());
		System.out.println("Age: "+person.getAge());
		System.out.println("Designation: "+manager.getDesignation());
		System.out.println("===========Manager========");
		
		System.out.println("Lookup - component - param level : "+context);
		System.out.println("Lookup - component - param level : "+address);
		//These are resolving to null
		System.out.println("Lookup - component - instance level : "+this.context);
		System.out.println("Lookup - component - instance level : "+this.address);
		
		//These two resolved successfully
		System.out.println("inject - component : "+injectedContext);
		System.out.println("inject - component : "+injectedAddress);
		return employee;
	}
}
