package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class HashTableTest {
	@Test
	public void testExists() {
		HashTable<Integer, String> table = new HashTable<>();


		assertFalse(table.exists(0));
		table.add(0, "XMCHX");
		assertTrue(table.exists(0));
	}

	@Test
	public void testGet() {
		HashTable<Integer, String> table = new HashTable<>(4);
		table.add(1, "x");
		table.add(2, "xm");
		table.add(3, "xmc");
		table.add(5, "xmch");
		table.add(6, "xmchx");

		assertEquals("xmchx", table.get(2));
		assertEquals("xmch", table.get(1));
		assertEquals("xmc", table.get(3));
	}

	@Test
	public void testRemove() {
		HashTable<String, String> table = new HashTable<>(4);
		table.add("a", "a");
		table.add("b", "b");
		table.add("c", "c");

		assertEquals("a", table.remove("a"));
		assertEquals("b", table.remove("b"));
		assertEquals("c", table.remove("c"));
	}

	@Test
	public void testCount() {
		HashTable<String, Integer> hashTable = new HashTable<>(26);
		hashTable.add("X", 1);
		hashTable.add("M", 2);
		hashTable.add("C", 3);
		hashTable.add("H", 4);
		hashTable.add("X", 5);

		assertEquals(4, hashTable.size());
		hashTable.remove("A");
		assertEquals(4, hashTable.size());
	}
}
