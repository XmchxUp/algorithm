package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class LinkedListTest {
	@Test
	public void test() {
		LinkedList<Integer> list = new LinkedList<>();

		list.pushFront(20);
		list.pushFront(10);


		assertEquals(2, list.size());
		assertFalse(list.empty());

		assertEquals(list.valueAt(0), 10);
		assertEquals(list.valueAt(1), 20);

		assertEquals(10, list.popFront());
		assertEquals(20, list.popFront());

		assertTrue(list.empty());

		list.pushBack(1);
		list.pushBack(2);
		list.pushFront(0);

		assertEquals(0, list.front());
		assertEquals(2, list.back());

		assertEquals(2, list.popBack());
		assertEquals(1, list.popBack());
		assertEquals(0, list.popBack());

		assertTrue(list.empty());

		list.insert(0, 1);
		list.insert(0, 2);
		list.insert(0, 3);
		list.insert(2, 4);
		list.insert(4, 5);
		// 3->2->4->1->5

		assertEquals(4, list.valueNFromEnd(3));
		assertEquals(3, list.valueNFromEnd(5));
		assertEquals(5, list.valueNFromEnd(1));

		assertEquals(4, list.valueAt(2));
		assertEquals(3, list.valueAt(0));

		assertEquals(3, list.erase(0));
		assertEquals(4, list.erase(1));
		assertEquals(5, list.erase(2));

		assertEquals(2, list.size());

		list.pushFront(0);
		list.pushBack(0);
		list.insert(2, 0);
		// 0->2->0->1->0

		assertEquals(0, list.removeValue(0));
		assertEquals(1, list.removeValue(0));
		assertEquals(2, list.removeValue(0));


		list.pushBack(0);
		list.pushFront(4);
		list.pushBack(3);
		// 4->2->1->0->3

		list.reverse();
		// 3->0->1->2->4
		assertEquals(3, list.valueAt(0));
		assertEquals(4, list.back());
		assertEquals(1, list.valueNFromEnd(3));
	}
}
