package com.datastructure;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		Array<Integer> myArray = new Array<Integer>();
		
		myArray.add(12);
		myArray.add(8);
		myArray.add(39);
		System.out.println(myArray.toString());
		
		System.out.println(myArray.size());
		System.out.println(myArray.get(1));
		
		myArray.set(2, 81);
		System.out.println(myArray.toString());
		
		myArray.removeAt(0);
		System.out.println(myArray.toString());
		
		myArray.remove(81);
		System.out.println(myArray.toString());
		
		myArray.add(101);
		System.out.println(myArray.toString());
		System.out.println(myArray.contains(101));
		System.out.println(myArray.contains(102));
		System.out.println(myArray.indexOf(101));
		
		
		Iterator<Integer> iterator = myArray.iterator();
		while(iterator.hasNext()) System.out.println(iterator.next());
		
		myArray.clear();
		System.out.println(myArray.toString());
	}

}
