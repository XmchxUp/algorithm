package io.github.xmchxup.datastructures;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class LinkedList<Item extends Comparable<Item>> {
	private static class Node<Item> {
		Item item;
		Node<Item> next;


		public Node(Item item, Node<Item> next) {
			this.item = item;
			this.next = next;
		}
	}

	private int size;
	private Node<Item> head;
	private Node<Item> tail;

	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}

	public int size() {
		return size;
	}

	public boolean empty() {
		return size() == 0;
	}

	public Item valueAt(int index) {
		checkIndex(index);

		Node<Item> p = head;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.item;
	}

	public void pushFront(Item item) {
		Node<Item> newNode = new Node<>(item, null);

		if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}

	public Item popFront() {
		if (empty()) throw new NoSuchElementException();

		Item item = head.item;
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
		}
		size--;
		return item;
	}

	public void pushBack(Item item) {
		Node<Item> newNode = new Node<>(item, null);

		if (size == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	public Item popBack() {
		if (empty()) throw new NoSuchElementException();

		Item item = tail.item;
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			Node<Item> prev = head;
			while (prev.next != tail) {
				prev = prev.next;
			}

			tail = prev;
			tail.next = null;
		}

		size--;
		return item;
	}

	public Item front() {
		if (empty()) throw new NoSuchElementException();
		return head.item;
	}

	public Item back() {
		if (empty()) throw new NoSuchElementException();
		return tail.item;
	}

	public void insert(int index, Item item) {
		checkInsertIndex(index);

		Node<Item> newNode = new Node<>(item, null);

		if (empty()) {
			head = newNode;
			tail = newNode;
			size++;
		} else if (index == 0) { // insert head
			pushFront(item);
		} else if (index == size) { // insert  tail
			pushBack(item);
		} else {
			Node<Item> prev = head;
			Node<Item> next;
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}
			next = prev.next;
			prev.next = newNode;
			newNode.next = next;
			size++;
		}


	}

	public Item erase(int index) {
		checkIndex(index);

		Item item;

		Node<Item> prev = head;
		Node<Item> curr;

		if (index == 0) { // remove head
			item = popFront();
		} else if (index == size - 1) { // remove tail
			item = popBack();
		} else {
			for (int i = 0; i < index - 1; i++) {
				prev = prev.next;
			}
			curr = prev.next;
			item = curr.item;
			prev.next = curr.next;
			size--;
		}
		return item;
	}

	public Item valueNFromEnd(int n) {
		checkIndex(n - 1);

		int idx = size - n;
		Node<Item> p = head;

		for (int i = 0; i < idx; i++) {
			p = p.next;
		}
		return p.item;
	}

	public void reverse() {
		Node<Item> tmp = tail;
		tail = reverse(head);
		head = tmp;
		// or use stack
	}

	private Node<Item> reverse(Node<Item> node) {
		if (node.next == null) {
			return node;
		}
		Node<Item> prev = reverse(node.next);
		node.next = null;
		prev.next = node;
		return node;
	}

	/**
	 * removes the first item in the list with this value
	 */
	public int removeValue(Item item) {
		if (empty()) throw new NoSuchElementException();

		int idx = 0;

		if (head.item == item) { // remove head
			popFront();
		} else {
			Node<Item> prev = head;
			Node<Item> cur = head;

			while (cur.item != item) {
				prev = cur;
				cur = cur.next;
				idx++;
			}

			prev.next = cur.next;
			if (cur == tail) { // remove tail
				tail = prev;
			}
			size--;
		}
		return idx;
	}

	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
	}

	private void checkInsertIndex(int idx) {
		if (idx < 0 || idx > size) throw new IndexOutOfBoundsException();
	}

	public static void main(String[] args) {
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
		assertEquals(3,list.valueAt(0));
		assertEquals(4,list.back());
		assertEquals(1,list.valueNFromEnd(3));
	}
}
