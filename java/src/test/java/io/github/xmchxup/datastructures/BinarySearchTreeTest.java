package io.github.xmchxup.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BinarySearchTreeTest {
	@Test
	void testAll() {
		// https://visualgo.net/zh/bst
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.insert(8);
		bst.insert(5);
		bst.insert(4);
		bst.insert(6);
		bst.insert(7);
		bst.insert(28);
		bst.insert(94);
		bst.insert(68);
		bst.insert(35);
		bst.insert(69);

		bst.printValues();

		assertEquals(10, bst.getNodeCount());

		assertFalse(bst.isInTree(11));
		assertFalse(bst.isInTree(100));
		assertTrue(bst.isInTree(35));
		assertTrue(bst.isInTree(94));

		assertEquals(5, bst.getHeight());

		assertEquals(4, bst.getMin());
		assertEquals(94, bst.getMax());

//		assertTrue(bst.isBinarySearchTree(Integer.MIN_VALUE, Integer.MAX_VALUE));
//		bst.update(7, 100);
//		assertFalse(bst.isBinarySearchTree(Integer.MIN_VALUE, Integer.MAX_VALUE));

		assertEquals(8, bst.getSuccessor(7));
		assertEquals(28, bst.getSuccessor(8));
		assertEquals(68, bst.getSuccessor(35));
		assertEquals(94, bst.getSuccessor(69));


		bst.deleteValue(7);
		bst.deleteValue(8);
		bst.deleteValue(69);

		assertEquals(7, bst.getNodeCount());
		assertTrue(bst.isBinarySearchTree(Integer.MIN_VALUE, Integer.MAX_VALUE));

		System.out.println();
		bst.printValues();


	}
}
