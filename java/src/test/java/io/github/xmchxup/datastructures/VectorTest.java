package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class VectorTest {
	private static final int DEFAULT_CAPACITY = 16;

	@Test
	public void test() {
		// IDEA Plugin: Java Visualizer
		Vector<Integer> vector = new Vector<>();
		vector.push(1);
		vector.push(2);
		vector.prepend(10);
		vector.insert(1, 3);
		vector.insert(2, 3);
		vector.insert(3, 4);


		// debug
		System.out.println();

		assertEquals(3, vector.at(2));
		assertEquals(1, vector.find(3));
		assertEquals(10, vector.at(0));
		assertEquals(6, vector.size());
		assertFalse(vector.isEmpty());
		assertEquals(DEFAULT_CAPACITY, vector.capacity());

		vector.delete(0);
		assertEquals(-1, vector.find(10));
		vector.remove(3);
		assertEquals(3, vector.size());

		// test resize
		for (int i = 0; i < 20; i++) {
			vector.prepend(i);
		}

		assertEquals(DEFAULT_CAPACITY << 1, vector.capacity());

		for (int i = 0; i < 20; i++) {
			vector.remove(i);
		}

		assertEquals(DEFAULT_CAPACITY, vector.capacity());
	}
}
