package com.datastructure.unionfind;

public class Main {

	public static void main(String[] args) {
		UnionFind unionFind = new UnionFind(10);
		System.out.println(unionFind);
		System.out.println(unionFind.size());
		System.out.println(unionFind.numComponents());
		
		unionFind.union(0, 5);
		unionFind.union(2, 8);
		unionFind.union(4, 6);
		System.out.println(unionFind);
		
		unionFind.union(0, 2);
		unionFind.union(8, 4);
		System.out.println(unionFind);
		System.out.println(unionFind.find(8));
		System.out.println(unionFind.isConnected(0, 8));
		System.out.println(unionFind.size());
		System.out.println(unionFind.numComponents());
	}

}
