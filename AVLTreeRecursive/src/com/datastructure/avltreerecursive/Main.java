package com.datastructure.avltreerecursive;

public class Main {

	public static void main(String[] args) {
		
		AVLTreeRecursive<Integer> tree = new AVLTreeRecursive<>();
		
		 
		tree.insert(1);
		tree.insert(5);
		tree.insert(8);
		tree.insert(9);
		tree.insert(22);
		tree.insert(44);
		tree.insert(15);
		tree.insert(18);
		tree.insert(11);
		tree.insert(32);

		System.out.println(tree.display());
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isEmpty());
		System.out.println(tree.contains(32));
		
		tree.remove(15);
		System.out.println(tree.display());
		
		System.out.println(tree.validateBSTInvarient(tree.root));
		
		java.util.Iterator<Integer> iterator = tree.iterator();
		while(iterator.hasNext())
			System.out.println(iterator.next());
		
	}

}
