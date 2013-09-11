package com.pincrack.test;

import computation.Computer;

import junit.framework.TestCase;

public class ComputationTest extends TestCase {
	
	public void testGetIncrement() {
		int[] confidence = {0, 1, 2, 3, 4, 5, 6};
		int[] expected_increments = {10, 8, 6, 4, 2, 1, 10};
		
		for (int i = 0; i < confidence.length; i++) {
			assertEquals(Computer.getIncrement(confidence[i]), expected_increments[i]);
		}		
	}

}

