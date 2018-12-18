package com.gmail.danslclo;

import java.util.regex.Pattern;

public class Calculator {
	//private static final Pattern PATTERN_EXTRACT_OPERATION = Pattern.compile("^([^+*/-]+)([^\\d])([^+*/-]+)$");
	private static final Pattern PATTERN_CLEAN_ZERO_DECIMALS = Pattern.compile("\\.0*$");
	public static enum Operator{
		OPERATION_ADD("+"),
		OPERATION_SUBSTRACT("-"),
		OPERATION_DIVISE("/"),
		OPERATION_MULTIPLY("*");
		
		private String operator;
		private Operator(String operator) {
			this.operator = operator;
		}
		String getSymbol() {
			return operator;
		}
		public static Operator getOperator(String opVal) {
			Operator opResult = null;
			for(Operator op : values()) {
				if(op.getSymbol().equals(opVal)) {
					opResult = op;
					break;
				}
			}
			return opResult;
		}
	}

	private static float operate(String numA, String numB) {
		Float floatA = null;
		Float floatB = null;
		Float result = 0F;
		Operator operation = null;
		if(numB == null) {
			String cleanedValue = Parsor.replaceRedundantOperator(numA);
			cleanedValue = Parsor.cleanOperation(cleanedValue);
			String leftValue = Parsor.extractLeftValue(cleanedValue);
			floatA = Float.parseFloat(leftValue);
			String remainingPart = Parsor.getRightPartValue(cleanedValue);
			String operator = Parsor.extractLeftOperand(remainingPart);
			if(remainingPart.isEmpty() || operator == null) {
				throw new IncompleteOperationError("Incomplete operation error");
			}
			operation = Operator.getOperator(operator);
			numB = Parsor.getRightPartOperand(remainingPart);
			if(numB.isEmpty()) {
				throw new IncompleteOperationError("Incomplete operation error");
			}
		}else {
			floatA = Float.parseFloat(numA);
			String operator = Parsor.extractLeftOperand(numB);
			operation = Operator.getOperator(operator);
			numB = Parsor.getRightPartOperand(numB);
			
		}
		if(!Parsor.isNumber(numB)){
			String cleanedValue = Parsor.cleanOperation(numB);
			String leftValue = Parsor.extractLeftValue(cleanedValue);
			floatB = Float.parseFloat(leftValue);
			numB = Parsor.getRightPartValue(cleanedValue);
			/*String operator = Parsor.extractLeftOperand(remainingPart);
			Operator op = Operator.getOperator(operator);*/
			//numB = Parsor.getRightPartOperand(remainingPart);
			//floatB = operate(leftValue, remainingPart, op);
		}else {
			floatB = Float.parseFloat(numB);
			numB = null;
		}
		
		switch (operation) {
			case OPERATION_ADD:
				result = floatA + floatB;
			break;
			case OPERATION_SUBSTRACT:
				result = floatA - floatB;
			break;
			case OPERATION_DIVISE:
				if(floatB == 0f) {
					throw new ZeroDivisionError("Attempt to divise by zero");
				}
				result = floatA / floatB;
			break;
			case OPERATION_MULTIPLY:
				result = floatA * floatB;
			break;
		}
		if(numB != null) {
			result = operate(result.toString(), numB);
		}
		return result;
	}
	
	public static String parseSimpleOperation(String operation) throws ZeroDivisionError {
		Float result = operate(operation, null);
		return PATTERN_CLEAN_ZERO_DECIMALS.matcher(result.toString()).replaceFirst("");
	}
}
