package io.github.xmchxup.datastructures;

import java.util.NoSuchElementException;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class LinkedListQueue<E> {
	private static class Node<E> {
		E value;
		Node<E> next;

		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}
	}

	private Node<E> head;
	private Node<E> tail;

	public LinkedListQueue() {
		head = null;
		tail = null;
	}

	public void enqueue(E value) {
		Node<E> newNode = new Node<>(value, null);
		if (empty()) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
	}

	public E dequeue() {
		if (empty()) throw new NoSuchElementException();

		E value = head.value;

		head = head.next;

		return value;
	}

	public boolean empty() {
		return head == null;
	}
}
