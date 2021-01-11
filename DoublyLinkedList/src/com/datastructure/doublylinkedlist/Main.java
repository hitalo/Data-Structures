package com.datastructure.doublylinkedlist;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

		list.add(50);
		list.addFirst(20);
		list.addLast(200);
		System.out.println(list.toString());
		System.out.println(list.size());
		System.out.println(list.contains(20));
		System.out.println(list.contains(25));
		System.out.println(list.peekFirst());
		System.out.println(list.peekLast());
		
		list.removeFirst();
		System.out.println(list.toString());
		list.removeLast();
		System.out.println(list.toString());
		
		list.add(180);
		list.add(440);
		System.out.println(list.toString());
		
		list.remove(50);
		System.out.println(list.toString());
		list.removeAt(0);
		System.out.println(list.toString());
		
		System.out.println(list.indexOf(440));
		
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		list.clear();
		System.out.println(list.toString());
		
	}

}
