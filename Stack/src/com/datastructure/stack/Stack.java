package com.datastructure.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {

	private LinkedList<T> list = new LinkedList<T>();
	
	public Stack() {}
	public Stack(T firstElement) { push(firstElement); }
	
	public int size() { return list.size(); }
	public boolean isEmpty() { return size() == 0; }
	public void push(T element) { list.addLast(element); }
	
	
	public T pop() {
		if(isEmpty()) throw new EmptyStackException();
		return list.removeLast();
	}
	
	
	public T peek() {
		if(isEmpty()) throw new EmptyStackException();
		return list.peekLast();
	}
	
	
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	
	
	@Override
	public String toString() {
		return list.toString();
	}
	
}
