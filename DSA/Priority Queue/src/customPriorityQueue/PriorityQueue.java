package customPriorityQueue;

import java.util.ArrayList;

public class PriorityQueue {
	Node start = null;
	
	static class Node {
		int data;
		int priority;
		Node next;
		Node(int value, int priority){
			this.data = value;
			this.priority = priority;
			next = null;
		}
	}
	
	public void enque(int data, int priority) {
		if(start == null) {
			Node node = new Node(data, priority);
			start = node;
		} else {
			start = push(start, data, priority);			
		}
	}
	
	public Node push(Node head, int data, int priority)
	{
	    Node first = (head);
	    Node temp = new Node(data, priority);
	    if ((head).priority > priority) {
	        temp.next = head;
	        (head) = temp;
	    }
	    else {
	        while (first.next != null && first.next.priority < priority) {
	        	first = first.next;
	        }
	        temp.next = first.next;
	        first.next = temp;
	    }
	    return head;
	}
	
	public void deque() {
		start = start.next;
	}
	
	public int peek() {
		return ((start == null) ? -1 : start.data);
	}
	
	public boolean contains(int value) {
		if(start == null) {
			return false;
		} else {
			Node temp = start;
			while(temp != null) {
				if(temp.data == value) {
					return true;
				}
				temp = temp.next;
			}
			return false;
		}
	}
	
	public int center() {
		Node slow = start;
	    Node fast = start;
	    while (fast != null && fast.next != null) {
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    return slow.data;
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
			System.out.print( " " + node.data);
			node = node.next;
		}
		System.out.println();
	}
}
