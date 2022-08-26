package io.github.xmchxup.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BinarySearchTest {
	@Test
	public void test() {
		int[] array = {1, 2, 3, 5, 6, 8};
		assertEquals(2, BinarySearch.binarySearch(array, 3));
		assertEquals(-1, BinarySearch.binarySearch(array, 4));
		assertEquals(-1, BinarySearch.binarySearch(array, 7));
		assertEquals(-1, BinarySearch.binarySearch(array, 9));

		assertEquals(4,BinarySearch.binarySearchRecursion(array,6));
		assertEquals(5,BinarySearch.binarySearchRecursion(array,8));
		assertEquals(-1,BinarySearch.binarySearchRecursion(array,0));
		assertEquals(-1,BinarySearch.binarySearchRecursion(array,4));
		assertEquals(-1,BinarySearch.binarySearchRecursion(array,7));
	}
}
