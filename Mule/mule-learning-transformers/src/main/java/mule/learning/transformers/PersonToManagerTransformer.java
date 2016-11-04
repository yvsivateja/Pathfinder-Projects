package mule.learning.transformers;

import javax.inject.Inject;

import mule.learning.beans.Address;
import mule.learning.beans.Manager;
import mule.learning.beans.Person;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;

public class PersonToManagerTransformer extends AbstractMessageTransformer {
	
	/*
	 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
	 * Because, there is a registry change happened in mule 3.7 which enables this.
	 */
	@Inject
	private MuleContext injectedContext;
	@Inject
	private Address injectedAddress;
	//These are not being looked up in both mule 3.6 and 3.7
	@Lookup
	private MuleContext context;
	@Lookup
	private Address address;
	
	public PersonToManagerTransformer(){
		super();
		registerSourceType(DataTypeFactory.create(Person.class));
		setReturnDataType(DataTypeFactory.create(Manager.class));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		Person person = (Person)message.getPayload();
		Manager manager = new Manager();
		manager.setPerson(person);
		manager.setDesignation("General Manager");
		
		/*
		 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
		 * Because, there is a registry change happened in mule 3.7 which enables this.
		 */
		System.out.println(injectedContext);
		System.out.println(injectedAddress);
		
		
		//These are not being looked up in both mule 3.6 and 3.7
		System.out.println(context);
		System.out.println(address);
		return manager;
	}

}
