package dylingfone;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DisplayTest {
	Display classBeingTested = new Display();
	
	@Test
	void testAspectRatioCalculator() {
		int expected = 321;
		double x = 474.0;
		double y = 474.0;
		double z = 321.0;
		 
		assertTrue(expected == classBeingTested.aspectRatioCalculator(x, y, z));
	}

}
