package com.datastructure.binarysearchtree;

import java.util.Iterator;

public class Main {

	public static void main(String args[]) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		tree.add(4);
		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(6);
		tree.add(5);
		tree.add(10);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		
		Iterator<Integer> iterator = tree.traverse(TreeTraversalOrder.IN_ORDER);
		while(iterator.hasNext()) System.out.println(iterator.next());
		
		System.out.println(tree.size());
		System.out.println(tree.isEmpty());
		System.out.println(tree.contains(7));
		System.out.println(tree.contains(12));
		System.out.println(tree.height());
		
		tree.remove(9);
		tree.remove(4);
		tree.remove(3);
		iterator = tree.traverse(TreeTraversalOrder.LEVEL_ORDER);
		while(iterator.hasNext()) System.out.println(iterator.next());
		System.out.println(tree.size());
		System.out.println(tree.height());
	}
}
