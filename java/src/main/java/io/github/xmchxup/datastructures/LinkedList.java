package io.github.xmchxup.datastructures;


import java.util.NoSuchElementException;


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
}
