package customHashTable;

public class Node {
	int key, value;
	Node next;
	
	public Node(int key, int value) {
		this.key=key;
		this.value=value;
		this.next=null;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public int getValue() {
		return value;
	}
}
