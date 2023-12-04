package customHashTable;

import java.util.ArrayList;

public class HashTable {
	public Node[] Data;
	public int capacity, size;
	
	public HashTable(int capacity) {
		this.capacity = capacity;
		Data = new Node[capacity];
		for(int i=0; i<Data.length; i++) {
			Data[i] = null;
		}
		size=0;
	}
	
	public void insert(int key, int value) {
		if(key!=0) {
			size++;
			Integer index = key;
			int hash = Hash(index);
			while(Data[hash] != null && Data[hash].key != key) {
				hash = (hash + 1) % capacity;
			}
			Data[hash] = new Node(key, value);
		}
	}
	
	private int Hash(Integer index) {
		return index.hashCode() % capacity;
	}
	
	public void delete(int key) {
		Integer index = key;
		int hash = Hash(index);
		
		Node head = Data[hash];
		
		while(head!=null) {
			if(head.key==key) {
				size--;
				this.Data[hash] = null;
			}
			head=head.next;
		}
	}
	
	public boolean contains(int key, int value) {
		for(Node node: Data) {
			if(node!=null) {
				int nodeValue=node.getValue();
				int nodeKey=node.getKey();
				if(nodeValue == value && nodeKey == key) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int valueByKey(int key) {
		Integer index = key;
		int hash = Hash(index);
		Node node = Data[hash];
		if(node!=null) {
			return node.getValue();
		}
		return 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public ArrayList<String> iterator() {
		ArrayList<String> iterator = new ArrayList<>();
		for(int i=0; i<this.capacity;i++) {
			if(Data[i]!=null) {
				iterator.add(Data[i].getKey() + ":" + Data[i].getValue());
			}
		}
		return iterator;
	}
	
	public void print() {
		String s="{";
		for(int i=0; i<this.capacity;i++) {
			if(Data[i]!=null) {
				s += "(" + Data[i].getKey() + ":" + Data[i].getValue() + ")";
			}
		}
		s += "}";
		System.out.println(s);
	}
}

