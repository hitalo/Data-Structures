package com.datastructure.minprimaryqueuebinary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// the tree is logically created with the indexes 
// 0 is parent of 1 and 2, 1 is parent of 3 and 4, ...
public class MinPrimaryQueueBinary<T extends Comparable<T>> {
	
	private int heapSize = 0;
	private int heapCapacity = 0;
	private List<T> heap = null;
	
	//map may help optimize the operations
	private Map<T, TreeSet<Integer>> map = new HashMap<>();
	
	public MinPrimaryQueueBinary() { this(1); }
	public MinPrimaryQueueBinary(int capacity) {heap = new ArrayList<>(heapCapacity);}
	
	
	public MinPrimaryQueueBinary(T[] elements) { 
		
		heapSize = heapCapacity = elements.length;
		heap = new ArrayList<>(heapCapacity);
		
		for(int i = 0 ; i < heapSize ; i++) {
			mapAdd(elements[i], i);
			heap.add(elements[i]);
		}
		
		//put the elements in order, heapify
		for(int i = Math.max(0, (heapSize/2)-1) ; i >= 0 ; i--) sink(i);
	}
	
	
	public MinPrimaryQueueBinary(Collection<T> elements) {
		this(elements.size());
		for(T element : elements) add(element);
	}
	
	
	public int size() { return heapSize; }
	public boolean isEmpty() { return size() == 0; }
	public T poll() { return removeAt(0); }
	
	
	public void clear() {
		for(int i = 0 ; i < heapSize; i++) heap.set(i, null);
		heapSize = 0;
		map.clear();
	}
	
	
	public T peek() {
		if(isEmpty()) return null;
		return heap.get(0);
	}	
	
	
	public boolean contains(T element) {
		if(element == null) return false;
		return map.containsKey(element);
	}
	
	
	public void add(T element) {
		if(element == null) throw new IllegalArgumentException();
		
		if(heapSize < heapCapacity) {
			heap.set(heapSize, element);
		} else {
			heap.add(element);
			heapCapacity++;
		}
		
		mapAdd(element, heapSize);
		swim(heapSize);
		heapSize++;
	}
	
	
	public boolean remove(T element) {
		if(element == null) return false;
		Integer index = mapGet(element);
		if(index != null) removeAt(index);
		return index != null;
	}
	
	
	// Check if tree starting at index is min heap
	// Use index = 0 to test the whole tree
	public boolean isMinHeap(int index) {
		if(index >= heapSize) return true;
		
		int leftChild = (index * 2) + 1; // index of left child
		int rightChild = (index * 2) + 2; // index of right child
		if(leftChild  < heapSize && !less(index, leftChild))  return false;
		if(rightChild < heapSize && !less(index, rightChild)) return false;
		
		return isMinHeap(leftChild) && isMinHeap(rightChild);
	}
	
	
	private boolean less(int i, int j) {
		return heap.get(i).compareTo(heap.get(j)) <= 0;
	}
	
	
	private void swap(int i, int j) {
		T elementI = heap.get(i);
		T elementJ = heap.get(j);
		heap.set(i, elementJ);
		heap.set(j, elementI);
		mapSwap(elementI, elementJ, i, j);
	}
	
	
	//bring nodes to top
	private void swim(int index) {
		int parent = (index - 1) / 2; //grab the index of the next parent
		
		//keep swimming while node is less than parent and didn't reach root
		while(index > 0 && less(index, parent)) {
			swap(parent, index);
			index = parent;
			parent = (index - 1) / 2;
		}
	}
	
	
	//bring nodes to bottom
	private void sink(int index) {
		while (true) {
			int leftChild = (index * 2) + 1; // index of left child
			int rightChild = (index * 2) + 2; // index of right child
			int smallest = leftChild;

			if (rightChild < heapSize && less(rightChild, leftChild))
				smallest = rightChild;

			//stop if out of bounds or can't sink index anymore
			if (leftChild >= heapSize || less(index, smallest)) break;
			
			swap(smallest, index);
			index = smallest;
		}
	}
	
	
	private T removeAt(int index) {
		if(isEmpty()) return null;
		
		heapSize--;
		T element = heap.get(index);
		swap(index, heapSize);
		
		heap.set(heapSize, null);
		mapRemove(element, heapSize);
		if(index == heapSize) return element; //last element removed
		
		T newElement = heap.get(index);
		sink(index); //try sink
		if(heap.get(index).equals(newElement)) swim(index); //try swim if didn't sink
		
		return element;
	}
	
	
	private void mapAdd(T value, int index) {
		TreeSet<Integer> set = map.get(value);
		
		if(set == null) { //value doesn't exist in the map yet
			set = new TreeSet<>();
			set.add(index);
			map.put(value, set);
		} else set.add(index);
	}
	
	
	private void mapRemove(T value, int index) {
		TreeSet<Integer> set = map.get(value);
		set.remove(index);
		if(set.size() == 0) map.remove(value);
	}
	
	
	private Integer mapGet(T value) {
		TreeSet<Integer> set = map.get(value);
		if(set != null) return set.last();
		return null;
	}
	
	
	private void mapSwap(T elementI, T elementJ, int indexI, int indexJ) {
		TreeSet<Integer> setI = map.get(elementI);
		TreeSet<Integer> setJ = map.get(elementJ);
		setI.remove(indexI);
		setJ.remove(indexJ);
		setI.add(indexJ);
		setJ.add(indexI);
	}
	
	
	@Override
	public String toString() {
		return heap.toString();
	}
}
