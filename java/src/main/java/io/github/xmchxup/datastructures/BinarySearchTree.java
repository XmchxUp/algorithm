package io.github.xmchxup.datastructures;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BinarySearchTree<E extends Comparable<E>> {
	private int size;
	private Node<E> root;

	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;

		public Node(E element) {
			this(element, null, null);
		}

		public Node(E element, Node<E> left, Node<E> right) {
			this(element, left, right, null);
		}

		public Node(E element, Node<E> left, Node<E> right, Node<E> parent) {
			this.element = element;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

		public boolean isLeftChild() {
			return parent != null && parent.left == this;
		}

		public boolean isRightChild() {
			return parent != null && parent.right == this;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}

		public boolean hasTwoChildren() {
			return left != null && right != null;
		}

		public Node<E> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}

			if (isRightChild()) {
				return parent.left;
			}
			return null;
		}
	}

	public BinarySearchTree() {
		size = 0;
		root = null;
	}

	public void insert(E element) {
		Objects.requireNonNull(element);

		Node<E> newNode = new Node<>(element);

		if (root == null) {
			root = newNode;
			size++;
			return;
		}


		// 找到父节点
		Node<E> parent;
		Node<E> node = root;
		int cmp;
		do {
			parent = node;
			cmp = node.element.compareTo(element);
			if (cmp < 0) {
				node = node.right;
			} else if (cmp > 0) {
				node = node.left;
			} else { // 相等
				return;
			}
		} while (node != null);

		newNode.parent = parent;
		// 判断插入的元素位置
		if (cmp < 0) { // right subtree
			parent.right = newNode;
		} else if (cmp > 0) { // left subtree
			parent.left = newNode;
		}
		size++;
	}

	public int getNodeCount() {
		return size;
	}

	// prints the values in the tree, from min to max
	public void printValues() {
		inorder(System.out::println);
	}

	// returns true if given value exists in the tree
	public boolean isInTree(E element) {
		if (element == null) return false;
		return find(root, element) != null;
	}

	// returns the height in nodes (single node's height is 1)
	public int getHeight() {
		return height(root);
	}

	// returns the minimum value stored in the tree
	public E getMin() {
		if (getNodeCount() == 0) throw new NoSuchElementException();

		Node<E> node = root;
		while (node.left != null) {
			node = node.left;
		}
		return node.element;
	}

	// returns the maximum value stored in the tree
	public E getMax() {
		if (getNodeCount() == 0) throw new NoSuchElementException();

		Node<E> node = root;
		while (node.right != null) {
			node = node.right;
		}
		return node.element;
	}

	// 所有值应该在 (min, max)范围里，泛型实现不知道有啥好的解决方案（日后再说）。
	public boolean isBinarySearchTree(E min, E max) {
		return isBetween(root, min, max);
	}

	private boolean isBetween(Node<E> node, E min, E max) {
		if (node == null) return true;

		int cmp1 = node.element.compareTo(min);
		int cmp2 = node.element.compareTo(max);

		return cmp1 > 0 && cmp2 < 0 &&
				isBetween(node.left, min, node.element) &&
				isBetween(node.right, node.element, max);
	}

	public void deleteValue(E element) {
		Node<E> node = find(root, element);
		if (node == null) {
			throw new NoSuchElementException();
		}
		deleteNode(node);
	}

	public E getSuccessor(E element) {
		Node<E> node = find(root, element);
		if (node == null || getNodeCount() == 1) {
			throw new NoSuchElementException();
		}
		return successor(node).element;
	}

	// 方便测试isBinarySearchTree
	public void update(E oldValue, E newValue) {
		Node<E> node = root;
		while (node != null) {
			int cmp = node.element.compareTo(oldValue);

			if (cmp == 0) {
				node.element = newValue;
				return;
			} else if (cmp > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
	}

	private void deleteNode(Node<E> node) {
		if (node == null) return;
		size--;



		if (node.hasTwoChildren()) {
			// 交换 然后要删除后继节点
			Node<E> s = successor(node);
			node.element = s.element;
			node = s;
		}

		if (node.parent == null) { // root
			root = null;
			return;
		}

		Node<E> replacement = node.left != null ? node.left : node.right;


		if (replacement == null) { // no children
			if (node.isLeftChild()) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		} else {
			replacement.parent = node.parent;
			if (node.parent == null) { // root
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else { // node == node.parent.right
				node.parent.right = replacement;
			}
		}
	}

	private Node<E> successor(Node<E> node) {
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		while (node.parent != null && node.parent.right == node) {
			node = node.parent;
		}
		return node.parent;
	}

	private int height(Node<E> node) {
		if (node == null) return 0;
		return Math.max(height(node.left) + 1, height(node.right) + 1);
	}

	private Node<E> find(Node<E> node, E element) {
		if (node == null) return null;

		int cmp = node.element.compareTo(element);
		if (cmp == 0) {
			return node;
		} else if (cmp > 0) {
			return find(node.left, element);
		} else {
			return find(node.right, element);
		}
	}

	private void inorder(Consumer<E> c) {
		// use stack
		Deque<Node<E>> stack = new LinkedList<>();
		Node<E> node = root;
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				c.accept(node.element);
				node = node.right;
			}
		}
	}

}
