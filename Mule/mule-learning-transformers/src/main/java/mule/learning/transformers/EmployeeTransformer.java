package mule.learning.transformers;

import javax.inject.Inject;

import mule.learning.beans.Address;
import mule.learning.beans.Consultant;
import mule.learning.beans.Employee;
import mule.learning.beans.Manager;
import mule.learning.beans.Person;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;

public class EmployeeTransformer extends AbstractMessageTransformer implements DiscoverableTransformer{
	
	/*
	 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
	 * Because, there is a registry change happened in mule 3.7 which enables this.
	 */
	@Inject
	private MuleContext injectedContext;
	@Inject
	private Address injectedAddress;
	//These two were not resolved in both mule 3.6 and 3.7
	@Lookup
	private MuleContext context;
	@Lookup
	private Address address;
	
	public EmployeeTransformer(){
		super();
		//setName("et");
		registerSourceType(DataTypeFactory.create(Person.class));
		setReturnDataType(DataTypeFactory.create(Employee.class));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		Person person = message.getPayload(Person.class);
		Consultant consultant = new Consultant();
		consultant.setPerson(person);
		consultant.setDesignation("Consultant");
		Manager manager = new Manager();
		manager.setPerson(person);
		manager.setDesignation("Manager");
		Employee employee = new Employee();
		employee.setConsultant(consultant);
		employee.setManager(manager);
		
		/*
		 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
		 * Because, there is a registry change happened in mule 3.7 which enables this.
		 */
		System.out.println(injectedContext);
		System.out.println(injectedAddress);
		//These two were not resolved in both mule 3.6 and 3.7
		System.out.println(context);
		System.out.println(address);
		
		return employee;
	}

	@Override
	public int getPriorityWeighting() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public void setPriorityWeighting(int weighting) {
		// TODO Auto-generated method stub
		
	}


}
