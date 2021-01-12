package com.datastructure.minprimaryqueuebinary;

public class Main {

	public static void main(String[] args) {
		MinPrimaryQueueBinary<Integer> pqueue = new MinPrimaryQueueBinary<Integer>();

		pqueue.add(1);
		pqueue.add(2);
		pqueue.add(3);
		pqueue.add(4);
		pqueue.add(5);
		pqueue.add(6);
		pqueue.add(7);
		System.out.println(pqueue);
		System.out.println(pqueue.isMinHeap(0));
		System.out.println(pqueue.size());
		System.out.println(pqueue.isEmpty());
		
		pqueue.poll();
		System.out.println(pqueue);
		System.out.println(pqueue.peek());
		System.out.println(pqueue.contains(5));
		System.out.println(pqueue.contains(1));
		
		pqueue.remove(4);
		System.out.println(pqueue);
		System.out.println(pqueue.isMinHeap(0));
		
		pqueue.add(8);
		System.out.println(pqueue);
		System.out.println(pqueue.isMinHeap(0));
		
		pqueue.clear();
		System.out.println(pqueue);
	}

}
