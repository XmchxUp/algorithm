package io.github.xmchxup.datastructures;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
@SuppressWarnings("unchecked")
public class MaxHeap<E> {
	private static final int DEFAULT_CAPACITY = 16;
	private E[] items;
	private int size;

	private Comparator<E> comparator;

	private int compare(E e1, E e2) {
		return comparator == null
				? ((Comparable<E>) e1).compareTo(e2)
				: comparator.compare(e1, e2);
	}

	public MaxHeap() {
		this(null, DEFAULT_CAPACITY);
	}

	public MaxHeap(E[] elements) {
		Objects.requireNonNull(elements);
		int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
		this.items = (E[]) new Object[capacity];
		this.size = elements.length;
		System.arraycopy(elements, 0, this.items, 0, this.size);
		heapify();
	}

	public MaxHeap(Comparator<E> comparator) {
		this(comparator, DEFAULT_CAPACITY);
	}

	public MaxHeap(Comparator<E> comparator, int capacity) {
		this.comparator = comparator;
		this.items = (E[]) new Object[capacity];
		this.size = 0;
	}

	public void insert(E item) {
		itemEmptyCheck(item);
		ensureCapacity(size + 1);
		items[size++] = item;
		shiftUp(size - 1);
	}

	public int getSize() {
		return size;
	}

	public E getMax() {
		return items[0];
	}

	public E extractMax() {
		return remove(0);
	}

	public E remove(int idx) {
		emptyCheck();
		if (idx >= size) {
			throw new IndexOutOfBoundsException();
		}

		int lastIndex = size - 1;
		E item = items[idx];
		items[idx] = items[lastIndex];
		items[lastIndex] = null;

		size--;
		shiftDown(idx);
		return item;
	}

	public void heapify() {
		for (int i = (size >> 1) - 1; i >= 0; i--) {
			shiftDown(i);
		}
	}

	public static <E> void heapSort(E[] items) {
		MaxHeap<E> maxHeap = new MaxHeap<>(items);
		while (!maxHeap.isEmpty()) {
			System.out.println(maxHeap.extractMax());
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void ensureCapacity(int capacity) {
		int oldCapacity = items.length;
		if (oldCapacity >= capacity) return;

		capacity = oldCapacity + (oldCapacity >> 1);
		E[] newItems = (E[]) new Object[capacity];

		for (int i = 0; i < size; i++) {
			newItems[i] = items[i];
		}
		items = newItems;
	}

	private int parentIndex(int idx) {
		return (idx - 1) >> 1;
	}

	private int leftChildIndex(int idx) {
		return (idx << 1) + 1;
	}

	private int rightChildIndex(int idx) {
		return (idx << 1) + 2;
	}

	private void shiftUp(int index) {
		E item = items[index];
		while (index > 0) {
			int pIdx = parentIndex(index);
			if (compare(items[index], items[pIdx]) <= 0) {
				break;
			}
			items[index] = items[pIdx];
			index = pIdx;
		}
		items[index] = item;
	}

	private void shiftDown(int index) {
		E item = items[index];
		int halfSize = size >> 1;
		while (index < halfSize) {
			// 默认选取左边节点
			int childIndex = leftChildIndex(index);
			E childItem = items[childIndex];

			int rightChildIdx = rightChildIndex(index);

			if (rightChildIdx < size && compare(childItem, items[rightChildIdx]) < 0) {
				childItem = items[rightChildIdx];
				childIndex = rightChildIdx;
			}

			if (compare(item, childItem) >= 0) break;
			items[index] = childItem;
			index = childIndex;
		}
		items[index] = item;
	}

	private void emptyCheck() {
		if (size == 0) {
			throw new NoSuchElementException("empty ehap");
		}
	}

	private void itemEmptyCheck(E item) {
		if (item == null) {
			throw new NullPointerException("item must not be null");
		}
	}
}
