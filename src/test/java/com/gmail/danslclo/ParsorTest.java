package com.gmail.danslclo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParsorTest {

	@Test
	void testChars() {
		boolean isValid = Parsor.isOperationValid("1 + 1 a");
		assertFalse(isValid);
	}

	@Test
	void testValidOperationWithSpaces() {
		boolean isValid = Parsor.isOperationValid("1 + 1");
		assertTrue(isValid);
	}
	
	@Test
	void testReplaceRedundantOperators() {
		String result = Parsor.replaceRedundantOperator("1+-1--6");
		assertEquals("1-1+6", result);
	}
	
	@Test
	void testAddingOperatorBeforeParenthesis() {
		String transfoStr = Parsor.addMultiplicator("-7(2+6)");
		assertEquals("-7*(2+6)", transfoStr);;
	}
	
	@Test
	void testAddingOneBeforeParenthesisWhenNegativeNoNumber() {
		String transfoStr = Parsor.addOneWhenNoNumberBeforeParenthesisOperation("-(2+6)");
		assertEquals("-1*(2+6)", transfoStr);
	}
	
	@Test
	void testNotAddingOneBeforeParenthesisWhenPositiveNoNumber() {
		String transfoStr = Parsor.addOneWhenNoNumberBeforeParenthesisOperation("+(2+6)");
		assertEquals("+(2+6)", transfoStr);
	}
	
	@Test
	void testExtractLeftValue() {
		String transfoStr = Parsor.extractLeftValue("-22+6");
		assertEquals("-22", transfoStr);
	}
	
	@Test
	void testGetRightPartValue() {
		String transfoStr = Parsor.getRightPartValue("-22+6");
		assertEquals("+6", transfoStr);
	}
	
	@Test
	void testExtractLeftOperator() {
		String transfoStr = Parsor.extractLeftOperand("*6");
		assertEquals("*", transfoStr);
	}
	
	@Test
	void testIsNumber() {
		boolean isNumber = Parsor.isNumber("6.00004");
		assertTrue(isNumber);
	}
}
