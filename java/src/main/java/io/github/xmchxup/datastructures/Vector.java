package io.github.xmchxup.datastructures;

import java.util.NoSuchElementException;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Vector<T extends Comparable<T>> {
	private static final int DEFAULT_CAPACITY = 16;
	private int size;
	private T[] items;


	public Vector() {
		this(DEFAULT_CAPACITY);
	}

	public Vector(int capacity) {
		size = 0;
		items = (T[]) new Comparable[capacity];
	}

	/**
	 * number of items
	 */
	public int size() {
		return size;
	}

	/**
	 * number of items it can hold
	 */
	public int capacity() {
		return items.length;
	}


	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * returns item at given index, blows up if index out of bounds
	 */
	public T at(int index) {
		checkIndex(index);
		return items[index];
	}

	public void push(T item) {
		if (size == capacity()) resize(size << 1);
		items[size++] = item;
	}

	public void insert(int index, T item) {
		checkIndex(index);
		if (size == capacity()) resize(size << 1);

		for (int i = size; i > index; i--) {
			items[i] = items[i - 1];
		}
		items[index] = item;
		size++;
	}

	public void prepend(T item) {
		insert(0, item);
	}

	public T pop() {
		if (isEmpty()) throw new NoSuchElementException();

		T item = items[--size];
		items[size] = null;

		if (size * 4 <= capacity()) resize(capacity() >> 1);
		return item;
	}

	public void delete(int index) {
		if (isEmpty()) throw new NoSuchElementException();

		checkIndex(index);

		size--;
		for (int i = index; i < size; i++) {
			items[i] = items[i + 1];
		}
		items[size] = null;

		if (capacity() > DEFAULT_CAPACITY && size * 4 <= capacity()) resize(capacity() >> 1);
	}

	/**
	 * looks for value and removes index holding it (even if in multiple places)
	 */
	public void remove(T item) {
		int cmp;
		for (int i = 0; i < size; ) {
			cmp = items[i].compareTo(item);
			if (cmp == 0) {
				size--;
				for (int j = i; j < size ; j++) {
					items[j] = items[j + 1];
				}
				items[size] = null;
			} else {
				i++;
			}
		}
		if (capacity() > DEFAULT_CAPACITY && size * 4 <= capacity()) resize(capacity() >> 1);
	}

	/**
	 * looks for value and returns first index with that value, -1 if not found
	 */
	public int find(T item) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}


	private void checkIndex(int idx) {
		if (idx < 0 || idx >= items.length) throw new IndexOutOfBoundsException();
	}

	private void resize(int capacity) {
		T[] newItems = (T[]) new Comparable[capacity];
		for (int i = 0; i < size; i++) {
			newItems[i] = items[i];
		}
		items = newItems;
	}


}
