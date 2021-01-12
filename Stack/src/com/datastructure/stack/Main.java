package com.datastructure.stack;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.toString());
		System.out.println(stack.size());
		System.out.println(stack.isEmpty());
		
		stack.pop();
		stack.pop();
		System.out.println(stack.toString());
		
		Iterator<Integer> iterator = stack.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
