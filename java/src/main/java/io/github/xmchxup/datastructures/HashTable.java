package io.github.xmchxup.datastructures;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class HashTable<Key, Value> {
	private static final int DEFAULT_CAPACITY = 16;

	private int count;
	private int capacity;
	private Value[] table;

	public HashTable() {
		this(DEFAULT_CAPACITY);
	}

	public HashTable(int capacity) {
		this.count = 0;
		this.capacity = capacity;
		this.table = (Value[]) new Object[this.capacity];
	}

	public int hash(Key key) {
		return key.hashCode() * 31 % capacity;
	}

	public void add(Key key, Value value) {
		if (value == null) {
			throw new NullPointerException();
		}

		int index = hash(key);
		if (table[index] == null) {
			count++;
		}
		table[index] = value;
	}

	public Value get(Key key) {
		return table[hash(key)];
	}


	public boolean exists(Key key) {
		return table[hash(key)] != null;
	}

	public Value remove(Key key) {
		int index = hash(key);
		Value oldValue = table[index];
		table[index] = null;

		if (oldValue != null) {
			count--;
		}
		return oldValue;
	}

	public int size() {
		return count;
	}

}
