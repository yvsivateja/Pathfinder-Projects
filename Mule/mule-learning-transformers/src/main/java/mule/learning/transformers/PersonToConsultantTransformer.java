package mule.learning.transformers;

import javax.inject.Inject;

import mule.learning.beans.Address;
import mule.learning.beans.Consultant;
import mule.learning.beans.Person;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;

public class PersonToConsultantTransformer extends AbstractMessageTransformer implements DiscoverableTransformer{
	/*
	 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
	 * Because, there is a registry change happened in mule 3.7 which enables this.
	 */
	@Inject
	private MuleContext injectedContext;
	@Inject
	private Address injectedAddress;
	//These two were resolved to null in both mule 3.6 and 3.7
	@Lookup
	private MuleContext context;
	@Lookup
	private Address address;
	
	public PersonToConsultantTransformer(){
		super();
		registerSourceType(DataTypeFactory.create(Person.class));
		setReturnDataType(DataTypeFactory.create(Consultant.class));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		Person person = (Person)message.getPayload();
		Consultant consultant = new Consultant();
		consultant.setPerson(person);
		consultant.setDesignation("Senior Consultant");
		
		/*
		 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
		 * Because, there is a registry change happened in mule 3.7 which enables this.
		 */
		System.out.println(injectedContext);
		System.out.println(injectedAddress);
		//These two were resolved to null in both mule 3.6 and 3.7
		System.out.println(context);
		System.out.println(address);
		
		return consultant;
	}

	/**
	 * Weighting resolves conflicts when two or more transformers match the search criteria. 
	 * The weighting is a number between 1 and 10, with 10 being the highest priority. As a rule, Mule transformers have a priority of 1 and should always have a lower priority than any custom transformers.
	 */
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
