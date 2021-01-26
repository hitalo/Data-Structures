package com.datastructure.fenwicktree;

public class Main {

	public static void main(String[] args) {

		long[] values = { 1, 2, 3, 4, 5 , 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		FenwickTree tree = new FenwickTree(values);

		System.out.println(tree);
		System.out.println(tree.prefixSum(4));
		System.out.println(tree.sum(2, 10));
		
		tree.add(14, 8);
		System.out.println(tree);
		
		tree.set(4, 10);
		System.out.println(tree);
	}

}
