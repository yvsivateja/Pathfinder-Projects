package mule.learning.components;

import java.net.URL;

import org.mule.api.annotations.param.Payload;

public class SimpleComponent {

	public void logURL(@Payload URL url){
		System.out.println(url.getHost());
	}

}
