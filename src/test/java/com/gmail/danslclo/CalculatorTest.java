package com.gmail.danslclo;

import static org.junit.jupiter.api.Assertions.*;
import static com.gmail.danslclo.Calculator.Operator.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testAdd(){
		String addResult = Calculator.parseSimpleOperation("1.5+2");
		assertEquals("3.5", addResult);
	}

	@Test
	void testSubstract() {
		String minusResult = Calculator.parseSimpleOperation("1.5-2");
		assertEquals("-0.5", minusResult);
	}
	
	@Test
	void testDiviseByZero(){
		assertThrows(ZeroDivisionError.class, ()->{
			Calculator.parseSimpleOperation("2/0");
		});
	}
	
	@Test
	void testDiviseByZeroComplex(){
		assertThrows(ZeroDivisionError.class, ()->{
			Calculator.parseSimpleOperation("1-1+4*2/0");
		});
	}
	
	@Test
	void testWithRedundantOperators(){
		String result = Calculator.parseSimpleOperation("1--1+-4*2");
		assertEquals("-4", result);
	}
	
	@Test
	void testDivise() {
		String divResult = Calculator.parseSimpleOperation("4/2");
		assertEquals("2", divResult);
	}
	
	@Test
	void testMultiply() {
		String divResult = Calculator.parseSimpleOperation("4*2");
		assertEquals("8", divResult);
	}
	
	@Test
	void testExecuteStringAddition() {
		String addResult = Calculator.parseSimpleOperation("   4  + 2   ");
		assertEquals("6", addResult);
	}
	
	@Test
	void testExecuteStringSubstraction() {
		String addResult = Calculator.parseSimpleOperation("   4  - 2   ");
		assertEquals("2", addResult);
	}
	
	@Test
	void testExecuteStringDivision() {
		String addResult = Calculator.parseSimpleOperation("   4  / 2   ");
		assertEquals("2", addResult);
	}
	
	@Test
	void testExecuteStringMultiply() {
		String addResult = Calculator.parseSimpleOperation("   4  * 2   ");
		assertEquals("8", addResult);
	}
	
	@Test
	void testExecuteStringMultiplyNegative() {
		String addResult = Calculator.parseSimpleOperation("   4  * - 2   ");
		assertEquals("-8", addResult);
	}
	
	@Test
	void testExecuteThreeOperations() {
		String addResult = Calculator.parseSimpleOperation("   4  * 2   -7");
		assertEquals("1", addResult);
	}
	
	@Test
	void testExecuteIncompleteOperation() {
		assertThrows(IncompleteOperationError.class, ()->{
			Calculator.parseSimpleOperation("1");
		});
	}
	
	@Test
	void testExecuteIncompleteOperationWithOperator() {
		assertThrows(IncompleteOperationError.class, ()->{
			Calculator.parseSimpleOperation("1*");
		});
	}
}
