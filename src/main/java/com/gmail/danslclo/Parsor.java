package com.gmail.danslclo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parsor{
	private static final Pattern PATTERN_INVALID_CHAR = Pattern.compile("[^+\\*\\.\\d,/-]");
	private static final Pattern PATTERN_CLEAR_SPACES = Pattern.compile("\\s");
	private static final Pattern PATTERN_REPLACE_REDUNDANT_OPERATOR_MINUS = Pattern.compile("\\+\\s*\\-");
	private static final Pattern PATTERN_REPLACE_REDUNDANT_OPERATOR_PLUS = Pattern.compile("\\-\\s*\\-|\\+\\s*\\+");
	private static final Pattern PATTERN_ADD_MULTIPLICATOR_BEFORE_PARENTHESIS = Pattern.compile("(\\d)\\(");
	private static final Pattern PATTERN_ADD_ONE_BEFORE_PARENTHESIS = Pattern.compile("([-])\\(");
	private static final Pattern PATTERN_EXTRACT_LEFT_VALUE = Pattern.compile("^([+-]?[\\d\\.]+)");
	private static final Pattern PATTERN_EXTRACT_LEFT_OPERAND = Pattern.compile("^([\\-\\+]?[^\\d\\.])");
	private static final Pattern PATTERN_IS_NUMBER = Pattern.compile("^[\\+\\-]?[\\d\\.]+$");
	
	public static boolean isOperationValid(String operation) {
		String cleanedOperation = cleanOperation(operation);
		String cleanRedundant = replaceRedundantOperator(cleanedOperation);
		Matcher matcher = PATTERN_INVALID_CHAR.matcher(cleanRedundant);
		return !matcher.find();
	}
	
	public static String cleanOperation(String operation) {
		Matcher matcher = PATTERN_CLEAR_SPACES.matcher(operation);
		return matcher.replaceAll("");
	}
	
	public static String replaceRedundantOperator(String operation) {
		String result = PATTERN_REPLACE_REDUNDANT_OPERATOR_PLUS.matcher(operation).replaceAll("+");
		return PATTERN_REPLACE_REDUNDANT_OPERATOR_MINUS.matcher(result).replaceAll("-");
	}
	
	public static String addMultiplicator(String operation) {
		Matcher matcher = PATTERN_ADD_MULTIPLICATOR_BEFORE_PARENTHESIS.matcher(operation);
		return matcher.replaceAll("$1*(");
	}
	
	public static String addOneWhenNoNumberBeforeParenthesisOperation(String operation) {
		Matcher matcher = PATTERN_ADD_ONE_BEFORE_PARENTHESIS.matcher(operation);
		return matcher.replaceAll("$11*(");
	}
	
	public static String extractLeftValue(String leftOperand){
		String result = null;
		Matcher matcher = PATTERN_EXTRACT_LEFT_VALUE.matcher(leftOperand);
		if(matcher.find()) {
			result = matcher.group(1);
		}
		return result;
	}
	
	public static String extractLeftOperand(String leftOperand){
		String result = null;
		Matcher matcher = PATTERN_EXTRACT_LEFT_OPERAND.matcher(leftOperand);
		if(matcher.find()) {
			result = matcher.group(1);
		}
		return result;
	}
	
	public static String getRightPartValue(String leftOperand){
		Matcher matcher = PATTERN_EXTRACT_LEFT_VALUE.matcher(leftOperand);
		return matcher.replaceFirst("");
	}
	
	public static String getRightPartOperand(String leftOperand){
		Matcher matcher = PATTERN_EXTRACT_LEFT_OPERAND.matcher(leftOperand);
		return matcher.replaceFirst("");
	}
	
	public static boolean isNumber(String val) {
		Matcher matcher = PATTERN_IS_NUMBER.matcher(val);
		return matcher.matches();
	}
}
