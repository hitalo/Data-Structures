package com.datastructure.unionfind;

public class UnionFind {

	private int size = 0;
	private int[] componentSize;
	private int[] rootId;
	private int numComponents;
	
	public UnionFind(int size) {
		if(size <= 0) throw new IllegalArgumentException("Size <=0 is not allowed");
		
		this.size = numComponents = size;
		componentSize = new int[size];
		rootId = new int[size];
		for(int i = 0 ; i < size ; i++) {
			rootId[i] = i; //everybody is a root component in the beginning
			componentSize[i] = 1;
		}
	}
	
	//they are connected if they have the same root
	public boolean isConnected(int i, int j) { return find(i) == find(j); }
	
	public int componentSize(int id) { return componentSize[find(id)]; }
	public int size() { return size; }
	public int numComponents() { return numComponents; }
	
	
	public int find(int id) {
		int root = id;
		
		//find the root of that component
		while(rootId[root] != root) root = rootId[root];
		
		//optimize with path compression
		while(id != root) {
			int next = rootId[id];
			rootId[id] = root;
			id = next;
		}
		
		return root;
	}
	
	
	public void union(int id1, int id2) {
		int root1 = find(id1);
		int root2 = find(id2);
		
		if(root1 == root2) return;
		
		if(componentSize[root1] < componentSize[root2]) {
			componentSize[root2] += componentSize[root1];
			rootId[root1] = root2;
		} else {
			componentSize[root1] += componentSize[root2];
			rootId[root2] = root1;
		}
		numComponents--;
	}
	
	
	@Override
	public String toString() {
		int length = rootId.length;
		if(length == 0) return "[]";
		else {
			StringBuilder stringBuilder = new StringBuilder(length).append("[");
			for(int i = 0 ; i < length-1 ; i++)
				stringBuilder.append(rootId[i] + ", ");
			
			return stringBuilder.append(rootId[length-1] + "]").toString();
		}
	}
}
