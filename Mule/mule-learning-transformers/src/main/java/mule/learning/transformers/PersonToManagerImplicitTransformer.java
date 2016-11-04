package mule.learning.transformers;

import mule.learning.beans.Manager;
import mule.learning.beans.Person;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;
import org.mule.transformer.types.DataTypeFactory;

public class PersonToManagerImplicitTransformer extends AbstractMessageTransformer {
	
	private String prop;
	
	public PersonToManagerImplicitTransformer(){
		super();
		registerSourceType(DataTypeFactory.create(Person.class));
		setReturnDataType(DataTypeFactory.create(Manager.class));
	}

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		System.out.println(prop);
		/*
		 * When we say something like following mule will try to convert the payload to requested type
		 * i.e., in this case mule will try to convert the payload to Manager. As we have already registered a transformer 
		 * to convert Person to Manager (transformToManager method in AnnotatedTransformer class), mule will convert our payload to Manager type.
		 * If mule could not find any, it will throw an exception. 
		 */
		System.out.println("Implicit transformer");
		return message.getPayload(Manager.class);
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

}
