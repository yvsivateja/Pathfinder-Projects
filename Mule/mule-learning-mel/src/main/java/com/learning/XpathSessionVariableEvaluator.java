package com.learning;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.transport.PropertyScope;
import org.mule.expression.AbstractExpressionEvaluator;
import org.mule.expression.ExpressionUtils;

public class XpathSessionVariableEvaluator extends AbstractExpressionEvaluator implements MuleContextAware {

	private final static String NAME = "XSes";
	private MuleContext context;
	
	@Override
	public Object evaluate(String expression, MuleMessage message) {
		@SuppressWarnings("deprecation")
		String xpathResult = (String)context.getExpressionManager().evaluate("xpath3('"+expression+"')", message);
		return ExpressionUtils.getProperty(xpathResult, PropertyScope.SESSION,message);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.context = context;
	}

}
