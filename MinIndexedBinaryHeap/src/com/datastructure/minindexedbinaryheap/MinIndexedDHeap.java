package com.datastructure.minindexedbinaryheap;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinIndexedDHeap<T extends Comparable<T>> {

	private int size;
	
	// Number of elements in the heap
	private final int N;
	
	// Degree of every node in the heap
	private final int D;
	
	private final int[] child, parent;
	
	private final int[] positionMap;
	private final int[] inverseMap;
	private final Object[] values;
	
	public MinIndexedDHeap(int degree, int maxSize) {
		if(maxSize <= 0) throw new IllegalArgumentException("maxSize <= 0");
		
		D = max(2, degree);
		N = max(D+1, maxSize);
		
		inverseMap = new int[N];
		positionMap = new int[N];
		child = new int[N];
		parent = new int[N];
		values = new Object[N];
		
		for(int i = 0 ; i < N ; i++) {
			parent[i] = (i-1) / D;
			child[i] = i*D + 1;
			positionMap[i] = inverseMap[i] = -1;
		}
	}
	
	
	public int size() { return size; }
	public boolean isEmpty() { return size() == 0; }
	
	
	public boolean contains(int keyIndex) {
		keyInBoundsOrThrow(keyIndex);
		return positionMap[keyIndex] != -1;
	}
	
	
	public int peekMinKeyIndex() {
		isNotEmptyOrThrow();
		return inverseMap[0];
	}
	
	
	public int pollMinKeyIndex() {
		int minKeyIndex = peekMinKeyIndex();
		delete(minKeyIndex);
		return minKeyIndex;
	}
	
	
	public T peekMinValue() {
		isNotEmptyOrThrow();
		return (T) values[inverseMap[0]];
	}
	
	
	public T pollMinValue() {
		T minValue = peekMinValue();
		delete(peekMinKeyIndex());
		return minValue;
	}
	
	
	public void insert(int keyIndex, T value) {
		if(contains(keyIndex)) 
			throw new IllegalArgumentException("Index already exists: " + keyIndex);
		valueNotNullOrThrow(value);
		positionMap[keyIndex] = size;
		inverseMap[size] = keyIndex;
		values[keyIndex] = value;
		swim(size++);
	}
	
	
	public T valueOf(int keyIndex) {
		keyExistsOrThrow(keyIndex);
		return (T) values[keyIndex];
	}
	
	
	public T delete(int keyIndex) {
		keyExistsOrThrow(keyIndex);
		final int i = positionMap[keyIndex];
		swap(i, --size);
		sink(i);		// Check if sink is needed
		swim(i);		// Check if swim is needed
		T value = (T) values[keyIndex];
		values[keyIndex] = null;
		positionMap[keyIndex] = -1;
		inverseMap[size] = -1;
		return value;
	}
	
	
	public T update(int keyIndex, T value) {
		keyExistsAndValueNotNullOrThrow(keyIndex, value);
		final int i = positionMap[keyIndex];
		T oldValue = (T) values[keyIndex];
		values[keyIndex] = value;
		sink(i);
		swim(i);
		return oldValue;
	}
	
	
	// Updates only to decrease
	public void decrease(int keyIndex, T value) {
		keyExistsAndValueNotNullOrThrow(keyIndex, value);
		if(less(value, values[keyIndex])) {
			values[keyIndex] = value;
			swim(positionMap[keyIndex]);
		}
	}
	
	
	// Updates only to increase
	public void increase(int keyIndex, T value) {
		keyExistsAndValueNotNullOrThrow(keyIndex, value);
		if(less(values[keyIndex], value)) {
			values[keyIndex] = value;
			sink(positionMap[keyIndex]);
		}
	}
	
	
	private void sink(int i) {
		for(int j = minChild(i) ; j != -1 ;) {
			swap(i, j);
			i = j;
			j = minChild(i);
		}
	}
	
	
	private void swim(int i) {
		while(less(i, parent[i])) {
			swap(i, parent[i]);
			i = parent[i];
		}
	}
	
	
	private int minChild(int i) {
		int index = -1, from = child[i], to = min(size, from + D);
		for(int j = from; j < to; j++) 
			if(less(j, i)) index = i = j;
		return index;
	}
	
	
	private void swap(int i, int j) {
		positionMap[inverseMap[j]] = i;
		positionMap[inverseMap[i]] = j;
		int tmp = inverseMap[i];
		inverseMap[i] = inverseMap[j];
		inverseMap[j] = tmp;
	}
	
	
	// Tests if the value of node i < node j
	  @SuppressWarnings("unchecked")
	  private boolean less(int i, int j) {
	    return ((Comparable<? super T>) values[inverseMap[i]]).compareTo((T) values[inverseMap[j]]) < 0;
	  }

	  @SuppressWarnings("unchecked")
	  private boolean less(Object obj1, Object obj2) {
	    return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
	  }

	  @Override
	  public String toString() {
	    List<Integer> lst = new ArrayList<>(size);
	    for (int i = 0; i < size; i++) lst.add(inverseMap[i]);
	    return lst.toString();
	  }

	  /* Helper functions to make the code more readable. */

	  private void isNotEmptyOrThrow() {
	    if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
	  }

	  private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
	    keyExistsOrThrow(ki);
	    valueNotNullOrThrow(value);
	  }

	  private void keyExistsOrThrow(int ki) {
	    if (!contains(ki)) throw new NoSuchElementException("Index does not exist; received: " + ki);
	  }

	  private void valueNotNullOrThrow(Object value) {
	    if (value == null) throw new IllegalArgumentException("value cannot be null");
	  }

	  private void keyInBoundsOrThrow(int ki) {
	    if (ki < 0 || ki >= N)
	      throw new IllegalArgumentException("Key index out of bounds; received: " + ki);
	  }

	  /* Test functions */

	  // Recursively checks if this heap is a min heap. This method is used
	  // for testing purposes to validate the heap invariant.
	  public boolean isMinHeap() {
	    return isMinHeap(0);
	  }

	  private boolean isMinHeap(int i) {
	    int from = child[i], to = min(size, from + D);
	    for (int j = from; j < to; j++) {
	      if (!less(i, j)) return false;
	      if (!isMinHeap(j)) return false;
	    }
	    return true;
	  }
}
