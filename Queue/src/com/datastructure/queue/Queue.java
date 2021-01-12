package com.datastructure.queue;

import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {

	private LinkedList<T> list = new LinkedList<T>();
	
	public Queue() { }
	public Queue(T firstElement) { offer(firstElement); }
	
	
	public int size() { return list.size(); }
	public boolean isEmpty() { return size() == 0; }
	public void offer(T element) { list.addLast(element); }
	
	
	public T peek() {
		if(isEmpty()) throw new RuntimeException("Queue empty");
		return list.peekFirst();
	}
	
	
	public T poll() {
		if(isEmpty()) throw new RuntimeException("Queue empty");
		return list.removeFirst();
	}
	
	
	@Override
	public java.util.Iterator<T> iterator() {
		return list.iterator();
	}

	
	@Override
	public String toString() {
		return list.toString();
	}
}
