package com.datastructure.doublylinkedlist;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T>{

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	
	private class Node<T> {
		T data;
		Node<T> next, prev;
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		@Override
		public String toString() { return data.toString(); }
	}
	
	
	public int size() { return size; }
	public boolean isEmpty() { return size() == 0; }
	public void add(T element) { addLast(element); }
	public boolean contains(Object element) { return indexOf(element) != -1; }
	
	
	public void clear() {
		Node<T> traveler = head;
		while(traveler != null) {
			Node<T> next = traveler.next;
			traveler.next = traveler.prev = null;
			traveler.data = null;
			traveler = next;
		}
		traveler = head = tail = null;
		size = 0;
	}
	
	
	public void addFirst(T element) {
		if(isEmpty()) {
			head = tail = new Node<T>(element, null, null);
		} else {
			head.prev = new Node<T>(element, null, head);
			head = head.prev;
		}
		size++;
	}
	
	
	public void addLast(T element) {
		if(isEmpty()) {
			head = tail = new Node<T>(element, null, null);
		} else {
			tail.next = new Node<T>(element, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	
	public T peekFirst() {
		if(isEmpty()) throw new RuntimeException("Empty list");
		return head.data;
	}
	
	
	public T peekLast() {
		if(isEmpty()) throw new RuntimeException("Empty list");
		return tail.data;
	}
	
	
	public T removeFirst() {
		if(isEmpty()) throw new RuntimeException("Empty list");
		
		T data = head.data;
		head = head.next;
		--size;
		
		if(isEmpty()) tail = null; //if is empty now, set tail to null 
		else head.prev = null;
		
		return data;
	}
	
	
	public T removeLast() {
		if(isEmpty()) throw new RuntimeException("Empty list");
		
		T data = tail.data;
		tail = tail.prev;
		--size;
		
		if(isEmpty()) head = null; //if is empty now, set tail to null 
		else tail.next = null;
		
		return data;
	}
	
	
	//private since it will only be used internally
	private T remove(Node<T> node) {
		if(isEmpty()) return null; //if empty, do nothing
		
		if(node.prev == null) return removeFirst();
		if(node.next == null) return removeLast();
		
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		T data = node.data;
		
		node.prev = node.next = null;
		node.data = null;
		--size;
		
		return data;
	}
	
	
	public boolean remove(Object element) {
		Node<T> traveler = head;
		
		// Support searching for null
		if(element == null) {
			for(traveler = head ;  traveler != null ; traveler = traveler.next) {
				if(traveler.data == null) {
					remove(traveler);
					return true;
				}
			}
		} else {
			for(traveler = head ;  traveler != null ; traveler = traveler.next) {
				if(element.equals(traveler.data)) {
					remove(traveler);
					return true;
				}
			}
		}
		return false;
	}
	
	
	public T removeAt(int index) {
		if(index >= size || index < 0) throw new IllegalArgumentException();
		
		int i;
		Node<T> traveler;
		
		//check if it's better to start from head or tail
		if(index < size/2) {
			for(i = 0, traveler = head ; i != index ; i++) traveler = traveler.next;
		} else {
			for(i = size-1, traveler = tail ; i != index ; i--) traveler = traveler.prev;
		}
		return remove(traveler);
	}
	
	
	public int indexOf(Object element) {
		Node<T> traveler = head;
		int index = 0;
		
		// Support searching for null
		if(element == null) {
			for(traveler = head ;  traveler != null ; traveler = traveler.next, index++) {
				if(traveler.data == null) {
					return index;
				}
			}
		} else {
			for(traveler = head ;  traveler != null ; traveler = traveler.next, index++) {
				if(element.equals(traveler.data)) {
					return index;
				}
			}
		}
		return -1;
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node<T> traveler = head;
			public boolean hasNext() { return traveler != null; }
			public T next() {
				T data = traveler.data;
				traveler = traveler.next;
				return data; 
			}
		};
	}

	
	@Override
	public String toString() {
		if(size == 0) return "[]";
		StringBuilder stringBuilder = new StringBuilder().append("[");
		Node<T> traveler = head;
		while(traveler.next != null) {
			stringBuilder.append(traveler.data + ", ");
			traveler = traveler.next;
		}
		return stringBuilder.append(traveler.data + "]").toString();
	}
}
