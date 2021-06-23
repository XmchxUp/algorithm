package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class LinkedListQueueTest {
	@Test
	public void test() {
		LinkedListQueue<Integer> queue = new LinkedListQueue<>();

		assertTrue(queue.empty());
		queue.enqueue(10);
		queue.enqueue(11);
		queue.enqueue(12);
		assertFalse(queue.empty());

		assertEquals(10,queue.dequeue());
		assertEquals(11,queue.dequeue());
		assertEquals(12,queue.dequeue());
		assertTrue(queue.empty());
	}

}
