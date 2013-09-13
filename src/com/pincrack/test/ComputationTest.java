package com.pincrack.test;

import computation.Computer;

import junit.framework.TestCase;

public class ComputationTest extends TestCase {
	
	public void testGetIncrement() {
		int[] confidence = {0, 1, 2, 3, 4, 5, 6, -2};
		int[] expected_increments = {10, 8, 6, 4, 2, 1, 10, 10};
		
		for (int i = 0; i < confidence.length; i++) {
			assertEquals(Computer.getIncrement(confidence[i]), expected_increments[i]);
		}		
	}
	
	public void testSortPositions() {
		int[] confidence = {4, 4, 1, 0};
		int[] expected = {0, 1, 2, 3};
		assertTrue(compareArrays(Computer.sortPositions(confidence), expected));
		
		int[] confidence2 = {1, 5, 0, 3};
		int[] expected2 = {1, 3, 0, 2};
		assertTrue(compareArrays(Computer.sortPositions(confidence2), expected2));
		
		int[] confidence3 = {0, 2, 5, 4};
		int[] expected3 = {2, 3, 1, 0};
		assertTrue(compareArrays(Computer.sortPositions(confidence3), expected3));
		
		int[] confidence4 = {0, 0, 0, 0};
		int[] expected4 = {0, 1, 2, 3};
		assertTrue(compareArrays(Computer.sortPositions(confidence4), expected4));	
	}
	
	private boolean compareArrays(int[] a, int[] b) {
		if (a.length != b.length) return false;
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) return false;
		}
		
		return true;
		
	}

}

