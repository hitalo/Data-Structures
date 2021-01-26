package com.datastructure.fenwicktree;

public class FenwickTree {

	private long[] tree;
	
	public FenwickTree(int size) { tree = new long[size + 1]; }
	
	public FenwickTree(long[] values) {
		if(values == null) throw new IllegalArgumentException("Values array can't be null");
		
		tree = values.clone();
		for(int i = 1 ; i < tree.length ; i++) {
			int j = i + lsb(i);
			if(j < tree.length) tree[j] += tree[i];
		}
	}
	
	
	// Prefix sum of [1, i], one based
	public long prefixSum(int i) {
		long sum = 0L;
		while(i != 0) {
			sum += tree[i];
			i &= ~lsb(i); // same as i -= lsb(i)
		}
		return sum;
	}
	
	
	public long sum(int i, int j) {
		if(j < i) throw new IllegalArgumentException("Make sure j >= i");
		return prefixSum(j) - prefixSum(i - 1);
	}
	
	
	/// Add 'k' to index 'i'
	public void add(int i, long k ) {
		while(i < tree.length) {
			tree[i] += k;
			i += lsb(i);
		}
	}
	
	// Set index 'i' to be equal to 'k'
	public void set(int i, long k) {
		long value = sum(i, i);
		add(i, k - value);
	}
	
	
	// Returns the least significant bit (LSB)
	private int lsb(int number) {
		return number & -1;
		// alternative: return Integer.lowestOneBit(number);
	}
	
	
	@Override public String toString() {
		return java.util.Arrays.toString(tree);
	}
}
