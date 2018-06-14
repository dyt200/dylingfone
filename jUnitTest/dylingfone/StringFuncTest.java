package dylingfone;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringFuncTest {
	StringFunc classBeingTested;
	
	void setUp() {
		classBeingTested = new StringFunc();
	}
	 
	@Test
	void testIsValidDate() {
		String testValue = "01.01.1970";
		boolean expected = true;
		
		assertTrue(expected == classBeingTested.isValidDate(testValue));
	}

	@Test
	void testIsValidEmail() {
		String testValue = "pleaseGiveUs6@gmail.com";
		boolean expected = true;
		
		assertTrue(expected == classBeingTested.isValidEmail(testValue));
	}

	@Test
	void testIsValidPhoneNumber() {
		String testValue = "+41123123123";
		boolean expected = true;
		
		assertTrue(expected == classBeingTested.isValidPhoneNumber(testValue));
	}

}
