package com.datastructure.queue;

public class Main {

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<Integer>();
		
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		System.out.println(queue.toString());
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
		System.out.println(queue.peek());
		
		queue.poll();
		queue.poll();
		System.out.println(queue.toString());
		
		java.util.Iterator<Integer> iterator = queue.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
