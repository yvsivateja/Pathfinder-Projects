package com.learning;

import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextAware;
import org.mule.api.expression.ExpressionEvaluator;
import org.mule.expression.ExpressionUtils;
import org.mule.transformer.types.TypedValue;

public class XpathVariableEvaluator implements ExpressionEvaluator,MuleContextAware {

	private static final String NAME = "XVar";
	
	private MuleContext context;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}
	
	public void setName(String name){
		throw new UnsupportedOperationException("Cannot set name for custom evaluator");
	}

	@Override
	public Object evaluate(String expression, MuleMessage message) {
		String xpathResult = (String)context.getExpressionManager().evaluate(expression, "xpath", message, true);
		return ExpressionUtils.getPropertyWithScope(xpathResult, message);
	}

	@Override
	public TypedValue evaluateTyped(String expression, MuleMessage message) {
		String xpathResult = (String)context.getExpressionManager().evaluate(expression, "xpath", message, true);
		return ExpressionUtils.getTypedProperty(xpathResult, message);
	}

	@Override
	public void setMuleContext(MuleContext context) {
		this.context = context;
	}
	
	

}
