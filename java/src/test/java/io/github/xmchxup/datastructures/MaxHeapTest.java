package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class MaxHeapTest {

	@Test
	void testHeapSort() {
		Integer[] elements = {1, 25, 30, 41, 2, 3, 5, 6};
		MaxHeap.heapSort(elements);
	}

	@Test
	void testHeapify() {
		Integer[] elements = {1, 25, 30, 41, 2, 3, 5, 6};
		MaxHeap<Integer> maxHeap = new MaxHeap<>(elements);
		assertEquals(8, maxHeap.getSize());
		assertEquals(41, maxHeap.extractMax());
		assertEquals(30, maxHeap.getMax());
	}

	@Test
	void testRemoveAtIndex() {
		Integer[] elements = {1, 25, 30, 41, 2, 3, 5, 6};
		MaxHeap<Integer> maxHeap = new MaxHeap<>(elements);
		// 不知道removeAtIndex使用场景，可以去除，expected是测试给的
		assertEquals(3, maxHeap.remove(5));
		assertEquals(30, maxHeap.remove(2));
		assertEquals(25, maxHeap.remove(1));
	}

	@Test
	void testExtractMax() {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();
		maxHeap.insert(1);
		maxHeap.insert(5);
		maxHeap.insert(20);
		maxHeap.insert(3);
		maxHeap.insert(7);
		maxHeap.insert(8);

		assertEquals(20, maxHeap.extractMax());
		assertEquals(8, maxHeap.extractMax());
		assertEquals(7, maxHeap.extractMax());
		assertEquals(3, maxHeap.getSize());

		assertEquals(5, maxHeap.extractMax());
		assertEquals(3, maxHeap.extractMax());
		assertEquals(1, maxHeap.getSize());
		assertEquals(1, maxHeap.extractMax());
		assertTrue(maxHeap.isEmpty());

	}

	@Test
	void testInsert() {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();
		maxHeap.insert(1);
		maxHeap.insert(20);
		maxHeap.insert(3);
		maxHeap.insert(5);
		maxHeap.insert(7);
		maxHeap.insert(8);
		assertEquals(20, maxHeap.getMax());
		assertEquals(6, maxHeap.getSize());
		assertFalse(maxHeap.isEmpty());
	}
}
