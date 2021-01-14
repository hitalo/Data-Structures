package com.datastructure.binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>> {

	private int nodeCount = 0;
	private Node root;
	
	private class Node {
		T data;
		Node left, right;
		public Node(Node left, Node right, T element) {
			this.left = left;
			this.right = right;
			this.data = element;
		}
	}
	
	
	public int size() { return nodeCount; }
	public boolean isEmpty() { return size() == 0; }
	public boolean contains(T element) { return contains(root, element); }
	public int height() { return height(root); }
	
	
	public boolean add(T element) {
		if(contains(element)) return false; //our tree won't accept duplicates
		root = add(root, element);
		nodeCount++;
		return true;
	}
	
	
	public boolean remove(T element) {
		if(contains(element)) {
			root = remove(root, element);
			nodeCount--;
			return true;
		}
		return false;
	}
	
	
	private Node add(Node node, T element) {
		if(node == null) {
			node = new Node(null, null, element);
		} else {
			if(element.compareTo(node.data) < 0) {
				node.left = add(node.left, element);
			} else {
				node.right = add(node.right, element);
			}
		}
		return node;
	}
	
	
	private Node remove(Node node, T element) {
		if(node == null) return null;
		
		int compare = element.compareTo(node.data);
		
		if(compare < 0) {
			node.left = remove(node.left, element);
		} else if(compare > 0) {
			node.right = remove(node.right, element);
		} else {
			
			if(node.left == null) {
				Node rightChild = node.right;
				node.data = null;
				node = null;
				return rightChild;
			} else if(node.right == null) {
				Node leftChild = node.left;
				node.data = null;
				node = null;
				return leftChild;
			} else {
				//find the leftmost element in the right subtree
				Node temp = digLeft(node.right);
				//swap the data
				node.data = temp.data;
				//remove the element in the right subtree 
				node.right = remove(node.right, temp.data);
			}
		}
		return node;
	}
	
	
	private Node digLeft(Node node) {
		Node current = node;
		while(current.left != null) current = current.left;
		return current;
	}
	
	
	private Node digRight(Node node) {
		Node current = node;
		while(current.right != null) current = current.right;
		return current;
	}
	
	
	private boolean contains(Node node, T element) {
		if(node == null) return false;
		
		int compare = element.compareTo(node.data);
		
		if(compare < 0 ) return contains(node.left, element);
		else if(compare > 0 ) return contains(node.right, element);
		else return true;
	}
	
	
	private int height(Node node) {
		if(node == null) return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}
	
	
	
	/*
	 * Code below is from https://github.com/williamfiset/data-structures/blob/master/com/williamfiset/datastructures/binarysearchtree/BinarySearchTree.java
	 */
	
	
	
	public java.util.Iterator<T> traverse(TreeTraversalOrder order) {
		switch (order) {
			case PRE_ORDER: return preOrderTraversal();
			case IN_ORDER: return inOrderTraversal();
			case POST_ORDER: return postOrderTraversal();
			case LEVEL_ORDER: return levelOrderTraversal();
			default: return null;
		}
	}

	// Returns as iterator to traverse the tree in pre order
	private java.util.Iterator<T> preOrderTraversal() {

		final int expectedNodeCount = nodeCount;
		final java.util.Stack<Node> stack = new java.util.Stack<>();
		stack.push(root);

		return new java.util.Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				Node node = stack.pop();
				if (node.right != null)
					stack.push(node.right);
				if (node.left != null)
					stack.push(node.left);
				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Returns as iterator to traverse the tree in order
	private java.util.Iterator<T> inOrderTraversal() {

		final int expectedNodeCount = nodeCount;
		final java.util.Stack<Node> stack = new java.util.Stack<>();
		stack.push(root);

		return new java.util.Iterator<T>() {
			Node trav = root;

			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {

				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();

				// Dig left
				while (trav != null && trav.left != null) {
					stack.push(trav.left);
					trav = trav.left;
				}

				Node node = stack.pop();

				// Try moving down right once
				if (node.right != null) {
					stack.push(node.right);
					trav = node.right;
				}

				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Returns as iterator to traverse the tree in post order
	private java.util.Iterator<T> postOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final java.util.Stack<Node> stack1 = new java.util.Stack<>();
		final java.util.Stack<Node> stack2 = new java.util.Stack<>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			Node node = stack1.pop();
			if (node != null) {
				stack2.push(node);
				if (node.left != null)
					stack1.push(node.left);
				if (node.right != null)
					stack1.push(node.right);
			}
		}
		return new java.util.Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !stack2.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return stack2.pop().data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Returns as iterator to traverse the tree in level order
	private java.util.Iterator<T> levelOrderTraversal() {

		final int expectedNodeCount = nodeCount;
		final java.util.Queue<Node> queue = new java.util.LinkedList<>();
		queue.offer(root);

		return new java.util.Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !queue.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				Node node = queue.poll();
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
