package io.github.xmchxup.datastructures;

import java.util.NoSuchElementException;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class ArrayQueue<E> {
	private static final int DEFAULT_CAPACITY = 5;
	private static final int DEFAULT_ARRAY_SIZE = DEFAULT_CAPACITY + 1;
	private E[] items;
	// read/write position
	private int read;
	private int write;

	public ArrayQueue() {
		items = (E[]) new Object[DEFAULT_ARRAY_SIZE];
		read = (int) (Math.random() * DEFAULT_ARRAY_SIZE);
		write = read;
	}

	public void enqueue(E value) {
		if (full()) {
			throw new QueueFullException();
		}
		items[write] = value;
		write = index(write + 1);
	}

	public E dequeue() {
		if (empty()) {
			throw new NoSuchElementException();
		}

		E value = items[read];
		read = index(read + 1);
		return value;
	}

	public boolean empty() {
		return read == write;
	}

	public boolean full() {
		return index(write + 1) == read;
	}

	private int index(int idx) {
		return idx % DEFAULT_ARRAY_SIZE;
	}

	private static class QueueFullException extends RuntimeException {

	}
}
