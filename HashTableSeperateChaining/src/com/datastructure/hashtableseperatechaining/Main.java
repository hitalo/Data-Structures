package com.datastructure.hashtableseperatechaining;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		HashTableSeperateChaining<Integer, String> myTable = new HashTableSeperateChaining<>();

		myTable.add(0, "João");
		myTable.add(1, "Maria");
		myTable.add(2, "Lucas");
		myTable.add(3, "Hitalo");
		System.out.println(myTable);
		System.out.println(myTable.size());
		System.out.println(myTable.isEmpty());
		System.out.println(myTable.containsKey(2));
		System.out.println(myTable.keys());
		System.out.println(myTable.values());
		
		myTable.remove(0);
		System.out.println(myTable);
		
		Iterator<Integer> iterator = myTable.iterator();
		while(iterator.hasNext()) 
			System.out.println(myTable.get(iterator.next()));
		
		myTable.clear();
		System.out.println(myTable.size());
		
	}

}
