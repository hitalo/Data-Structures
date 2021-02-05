package com.datastructure.avltreerecursive;

import com.datastructure.utils.TreePrinter;

public class AVLTreeRecursive<T extends Comparable<T>> implements Iterable<T> {

	Node root;
	private int nodeCount = 0;
	
	
	public int size() { return nodeCount; }
	public boolean isEmpty() { return size() == 0; }
	public String display() { return TreePrinter.getTreeDisplay(root); }
	public boolean contains(T value) { return contains(root, value); }
	
	
	public int height() {
		if(root == null) return 0;
		return root.height;
	}
	
	
	public boolean insert(T value) {
		if(value == null) return false;
		if(!contains(root, value)) {
			root = insert(root, value);
			nodeCount++;
			return true;
		}
		return false;
	}
	
	
	public boolean remove(T value) {
		if(value == null) return false;
		if(contains(root, value)) {
			root = remove(root, value);
			nodeCount--;
			return true;
		}
		return false;
	}
	
	
	private boolean contains(Node node, T value) {
		if(node == null) return false;
		int cmp = value.compareTo(node.value);
		if(cmp < 0) return contains(node.left, value);
		if(cmp > 0) return contains(node.right, value);
		return true;
	}
	
	
	private Node insert(Node node, T value) {
		if(node == null) return new Node(value);
		int cmp = value.compareTo(node.value);
		if(cmp < 0) node.left = insert(node.left, value);
		else node.right = insert(node.right, value);
		update(node); 		// Update balance factor and height
		return balance(node);
	}
	
	
	private Node remove(Node node, T value) {
		if(node == null) return null;
		int cmp = value.compareTo(node.value);
		
		if(cmp < 0) {
			node.left = remove(node.left, value);
		} else if(cmp > 0) {
			node.right = remove(node.right, value);
		} else {
			
			if(node.left == null) {
				return node.right;
			} else if(node.right == null) {
				return node.left;
			} else {
				
				if(node.left.height > node.right.height) {
					T successorValue = findMax(node.left);
					node.value = successorValue;
					node.left = remove(node.left, successorValue);
				} else {
					T successorValue = findMin(node.right);
					node.value = successorValue;
					node.right = remove(node.right, successorValue);
				}
			}
		}
		update(node); 		// Update balance factor and height
		return balance(node);
	}
	
	
	private void update(Node node) {
		int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
		int rightNodeHeight = (node.right == null) ? -1 : node.right.height;
		node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
		node.balanceFactor = rightNodeHeight - leftNodeHeight;
	}
	
	
	private Node balance(Node node) {
		// Left heavy subtree
		if(node.balanceFactor == -2) {
			if(node.left.balanceFactor <= 0) return leftLeftCase(node);
			else return leftRightCase(node);
		
		// Right heavy subtree
		} else if(node.balanceFactor == +2) {				
			if(node.right.balanceFactor >= 0) return rightRightCase(node);
			else return rightLeftCase(node);
		}
		
		// Balanced already
		return node;
	}
	
	
	private Node leftLeftCase(Node node) {
		return rightRotation(node);
	}
	
	
	private Node leftRightCase(Node node) {
		node.left = leftRotation(node.left);
		return leftLeftCase(node);
	}
	
	
	private Node rightRightCase(Node node) {
		return leftRotation(node);
	}
	
	
	private Node rightLeftCase(Node node) {
		node.right = rightRotation(node.right);
		return rightRightCase(node);
	}
	
	
	private Node leftRotation(Node node) {
		Node newParent = node.right;
		node.right = newParent.left;
		newParent.left = node;
		update(node);
		update(newParent);
		return newParent;
	}
	
	
	private Node rightRotation(Node node) {
		Node newParent = node.left;
		node.left = newParent.right;
		newParent.right = node;
		update(node);
		update(newParent);
		return newParent;
	}
	
	
	private T findMin(Node node) {
		while(node.left != null) node = node.left;
		return node.value;
	}
	
	
	private T findMax(Node node) {
		while(node.right != null) node = node.right;
		return node.value;
	}
	
	
	class Node implements TreePrinter.PrintableNode {
		int balanceFactor;
		T value;
		int height;
		Node left, right;
		
		public Node(T value) { this.value = value; }
		
		@Override public Node getLeft() { return left; }
		@Override public Node getRight() { return right; }
		@Override public String getText() { return String.valueOf(value); }
	}
	
	
	// Code below is from https://github.com/williamfiset/data-structures/blob/master/com/williamfiset/datastructures/balancedtree/AVLTreeRecursive.java
	
	
	public java.util.Iterator<T> iterator() {

	    final int expectedNodeCount = nodeCount;
	    final java.util.Stack<Node> stack = new java.util.Stack<>();
	    stack.push(root);

	    return new java.util.Iterator<T>() {
	      Node trav = root;

	      @Override
	      public boolean hasNext() {
	        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
	        return root != null && !stack.isEmpty();
	      }

	      @Override
	      public T next() {

	        if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

	        while (trav != null && trav.left != null) {
	          stack.push(trav.left);
	          trav = trav.left;
	        }

	        Node node = stack.pop();

	        if (node.right != null) {
	          stack.push(node.right);
	          trav = node.right;
	        }

	        return node.value;
	      }

	      @Override
	      public void remove() {
	        throw new UnsupportedOperationException();
	      }
	    };
	  }

	
	  @Override
	  public String toString() {
	    return TreePrinter.getTreeDisplay(root);
	  }

	  
	  // Make sure all left child nodes are smaller in value than their parent and
	  // make sure all right child nodes are greater in value than their parent.
	  // (Used only for testing)
	  public boolean validateBSTInvarient(Node node) {
	    if (node == null) return true;
	    T val = node.value;
	    boolean isValid = true;
	    if (node.left != null) isValid = isValid && node.left.value.compareTo(val) < 0;
	    if (node.right != null) isValid = isValid && node.right.value.compareTo(val) > 0;
	    return isValid && validateBSTInvarient(node.left) && validateBSTInvarient(node.right);
	  }
}
