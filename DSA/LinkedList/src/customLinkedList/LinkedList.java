package customLinkedList;

import java.util.*;

public class LinkedList {
	
	Node start = null;
	
	static class Node {
		int data;
		Node next;
		Node(int value){
			this.data = value;
			next = null;
		}
	}
	
	public void insert(int data) {
		Node node = new Node(data);
		
		if(start == null) {
			start = node;
		} else {
			node.next = start;
			start = node;
		}
	}
	
	public void insertAtIndex(int data, int index) {
		Node node = new Node(data);
		Node currNode = start;
		for(int currIndex = 0; currIndex < index-1; currIndex++) {
			currNode = currNode.next;
		}
		Node temp = currNode.next;
		currNode.next = node;
		node.next = temp;
	}
	
	public void delete() {
		if(start == null) {
			System.out.println("Underflow");
		} else {
			start = start.next;			
		}
	}

	public void deleteAtIndex(int index) {
		Node prevNode = null;
		Node currNode = start;
		for(int currIndex = 0; currIndex < index; currIndex++) {
			prevNode = currNode;
			currNode = currNode.next;
		}
		prevNode.next = currNode.next;
	}
	
	public int center() {
		return middle(start).data;
	}
	
	private static Node middle(Node head) {
	    Node slow = head;
	    Node fast = head;
	    while (fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    return slow;
	}
	
	void sortLinkedList(Node head) {
	    Node current = head;
	    Node index = null;
	    int temp;

	    if (head == null) {
	      return;
	    } else {
	      while (current != null) {
	        index = current.next;

	        while (index != null) {
	          if (current.data > index.data) {
	            temp = current.data;
	            current.data = index.data;
	            index.data = temp;
	          }
	          index = index.next;
	        }
	        current = current.next;
	      }
	    }
	  }
	
	public void sort() {
		Node temp = start;
		sortLinkedList(temp);
	}
	
    public void reverse() {
        start = listReverse(start);
    }
    
    private static Node listReverse(Node head) {
        if(head == null) {
            return head;
        }
        
        if(head.next == null) {
            return head;
        }
  
        Node newHeadNode = listReverse(head.next);
        
        head.next.next = head;
        head.next = null;
        
        return newHeadNode;
    }
    
    public int size()
    {
    	Node currNode = start;
    	int size = 0;
    	while (currNode != null) {
    		currNode = currNode.next;
    		size++;
    	}
    	return size;
    }
    
    public ArrayList<Integer> iterator() {
		ArrayList<Integer> iterator = new ArrayList<>();
		Node currNode = start;
		while(currNode != null) {
			iterator.add(currNode.data);
			currNode = currNode.next;
		}
		return iterator;
	}
    
	public void print() {
		Node node = start;
		System.out.print(node.data);
		node = node.next;
		while(node != null) {
			System.out.print( "->" + node.data);
			node = node.next;
		}
		System.out.println();
	}
}
