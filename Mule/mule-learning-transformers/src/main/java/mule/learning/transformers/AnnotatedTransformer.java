package mule.learning.transformers;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import mule.learning.beans.Address;
import mule.learning.beans.Manager;
import mule.learning.beans.Person;

import org.mule.api.MuleContext;
import org.mule.api.annotations.ContainsTransformerMethods;
import org.mule.api.annotations.Transformer;
import org.mule.api.annotations.expression.Groovy;
import org.mule.api.annotations.expressions.Function;
import org.mule.api.annotations.expressions.Lookup;
import org.mule.api.annotations.param.InboundHeaders;
import org.mule.api.annotations.param.Payload;
import org.mule.api.registry.RegistrationException;

@ContainsTransformerMethods
public class AnnotatedTransformer {
	/*
	 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
	 * Because, there is a registry change happened in mule 3.7 which enables this.
	 */
	@Inject
	private MuleContext injectedContext;
	@Inject
	private Address injectedAddress;
	//These two are resolved to null, in both mule 3.6 and 3.7
	@Lookup
	private MuleContext context;
	@Lookup
	private Address address;
	
	/*
	 * The following two transformers does the same thing. But mule always chooses the second one.
	 * This is because, priorityWeighting of this transformer is less than that of second one
	 */
	
	/*@Transformer(priorityWeighting=8)
	public Manager transformToManager(@Payload Person person, @Lookup MuleContext context){
		Manager manager = new Manager();
		manager.setPerson(person);
		manager.setDesignation("Manager");
		System.out.println("Lookup - transformer - instance level : "+this.context);
		System.out.println("Lookup - transformer - param level : "+context);
		return manager;
	}*/
	//Default priorityWeighting of this transformer is 10, we can change at any time with 10 being the highest and 1 being the lowest priority
	@Transformer(priorityWeighting=10)
	public Manager transformToManager(@Payload Person person, @Function("uuid") String id, @Groovy("payload.name") String name,
			@InboundHeaders("*") List<String> allHeaders,
			@InboundHeaders("http-*") List<String> allHttpHeaders,
			@InboundHeaders("Content-Type") String contentType,
			@InboundHeaders("Content-Type,Host") List<String> multipleHeaderList,
			//Here loc is optional
			@InboundHeaders("Content-Type,Host,loc?") List<String> optionalHeaderList,
			@InboundHeaders("Content-Type,Host") Map<String,String> multipleHeaderMap,
			@InboundHeaders("Content-Type,Host,loc?") Map<String,String> optionalHeaderMap,
			
			@Lookup Address address,@Lookup MuleContext context) throws RegistrationException{
		Manager manager = new Manager();
		manager.setPerson(person);
		manager.setDesignation("Manager");
		System.out.println(id);
		System.out.println(name);
		
		System.out.println("========All Headers========");
		System.out.println(allHeaders);
		System.out.println("========All Headers========");
		System.out.println("Content-Type : "+contentType);
		System.out.println("========Multiple Headers========");
		System.out.println(multipleHeaderList);
		System.out.println("========Multiple Headers========");
		System.out.println("========Multiple Headers Optional========");
		System.out.println(optionalHeaderList);
		System.out.println("========Multiple Headers Optional========");
		System.out.println("========Multiple Headers Map========");
		System.out.println(multipleHeaderMap);
		System.out.println("========Multiple Headers Map========");
		System.out.println("========Multiple Headers Map Optional========");
		System.out.println(optionalHeaderMap);
		System.out.println("========Multiple Headers Map Optional========");
		
		
		System.out.println("Lookup - transformer - param level : "+context);
		System.out.println("Lookup - transformer - param level : "+address);
		
		//These two are resolved to null, in both mule 3.6 and 3.7
		System.out.println("Lookup - transformer - instance level : "+this.context);
		System.out.println("Lookup - transformer - instance level : "+this.address);
		
		/*
		 * These two are getting resolved to proper values in mule 3.7, but not in mule 3.6.
		 * Because, there is a registry change happened in mule 3.7 which enables this.
		 */
		System.out.println("inject - transformer : "+injectedContext);
		System.out.println("inject - transformer : "+injectedAddress);
		
		return manager;
	}
	
	/*
	 * Transformers can only have one return type but we can define multiple source types. 
	 * For example, lets say we might receive the input URL string as s java.lang.String or java.io.InputStream we could add the additional source type to the @Transformer annotation. 
	 * If mule receive InputStream mule does this auto-transformation(from InputStream to String) for us in background and then handles the result(urlString) to the transformToURL method. 
	 * Note that you can add a comma-separated list of source type classes.
	 */
	
	@Transformer(sourceTypes={InputStream.class,String.class})
	public URL transformToURL(String urlString) throws MalformedURLException{
		return new URL(urlString);
	}
}
