package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class ArrayQueueTest {
	@Test
	void test() {
		ArrayQueue<Integer> queue = new ArrayQueue<>();

		assertTrue(queue.empty());
		assertFalse(queue.full());

		// default 5 size
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);

		assertTrue(queue.full());
		assertFalse(queue.empty());

		assertEquals(10,queue.dequeue());
		assertEquals(20,queue.dequeue());
		assertEquals(30,queue.dequeue());
		assertEquals(40,queue.dequeue());

		assertFalse(queue.full());
		assertFalse(queue.empty());

		queue.enqueue(30);
		queue.enqueue(40);
		queue.enqueue(50);

		assertEquals(50,queue.dequeue());
		assertEquals(30,queue.dequeue());
		assertEquals(40,queue.dequeue());
		assertEquals(50,queue.dequeue());
		assertTrue(queue.empty());
		assertFalse(queue.full());
	}
}
