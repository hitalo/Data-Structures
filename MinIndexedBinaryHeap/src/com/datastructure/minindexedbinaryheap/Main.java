package com.datastructure.minindexedbinaryheap;

public class Main {

	public static void main(String[] args) {
		MinIndexedBinaryHeap<Integer> heap = new MinIndexedBinaryHeap<>(10);
		
		heap.insert(0, 1);
		heap.insert(1, 3);
		heap.insert(2, 12);
		heap.insert(3, 45);
		heap.insert(4, 19);
		heap.insert(5, 22);
		heap.insert(6, 13);
		heap.insert(7, 7);
		
		System.out.println(heap);
		System.out.println(heap.size());
		System.out.println(heap.isEmpty());
		System.out.println(heap.contains(6));
		System.out.println(heap.peekMinKeyIndex());
		System.out.println(heap.peekMinValue());
		System.out.println(heap.pollMinKeyIndex());
		System.out.println(heap.pollMinValue());
		System.out.println(heap);
		System.out.println(heap.valueOf(3));
		
		heap.update(3, 8);
		System.out.println(heap);
		
		heap.increase(7, 10);
		heap.decrease(3, 40);
		System.out.println(heap);
		System.out.println(heap.isMinHeap());
	}

}
