package customQueue;

import java.util.ArrayList;

public class Queue {
	
	Node start = null;
	Node end = null;
	
	static class Node {
		int data;
		Node next;
		Node(int value){
			this.data = value;
			next = null;
		}
	}
	
	public void enque(int data) {
		Node node = new Node(data);
		
		if(start == null) {
			start = node;
			end = node;
		} else {
			end.next = node;
			end = end.next;
		}
	}
	
	public void deque() {
		if(start == null) {
			System.out.println("Underflow");
		} else {
			start = start.next;			
		}
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
	
	/*int size;
	int start;
	int end;
	int arr[];
	public Queue(int size) {
		this.size = size;
		start = -1;
		end = -1;
		arr = new int[size];
	}
	
	public boolean isFull(Queue queue) {
		if(start == 0 && end == size - 1)
        {
            return true;
        }
        if(start == end + 1) 
        {
            return true;
        }
        return false;
	}
	
	public boolean isEmpty(Queue queue) {
		return start == -1;
	}
	
	public void enque(Queue queue, int data) {
		if(isFull(queue)) {
			System.out.println("Queue is full");
		} else {
			if(start == -1) {   
	            start = 0;
	        }
	        end = (end + 1) % size;   
	        arr[end] = data;
		}
	}
	
	public void deque(Queue queue) {
		if(isEmpty(queue)) {
			System.out.println("Queue is empty");
		} else {
	        if(start == end) {
	        	start = -1;
	            end = -1;
	        }
	        else {
	            start = (start + 1) % size;
	        }
		}
	}
	
	public int peek(Queue queue) {
		if(isEmpty(queue)) {
			System.out.println("Queue is empty");
			return -1;
		} else {
			return arr[start];
		}
	}
	
	public boolean contains(Queue queue, int value) {
		if(isEmpty(queue)) {
			return false;
		} else {
			int i = start;
			while(i != end) {
				if(arr[i] == value) {
					return true;
				}
				i++;
				if(i > size - 1) {
					i = 0;
				}
			}
			return false;
		}
	}
	
	public int size(Queue queue) {
		if(end >= start)
	    {
	        return (end - start) + 1;
	    }
	    else
	    {
	        return (size - (start - end) + 1);
	    }
	}
	
	public void reverse(Queue queue) {
		int i = start;
		int j = end;
		while(i != j) {
			
		}
	}
	
	public void print(Queue queue) {
		if(isEmpty(queue)) {
			System.out.println("Queue is empty");
		} else {
			System.out.print(arr[start] + " ");
			int i;
			for(i = start; i != end; ) {
				i = ++i % size;
	            System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}*/
}
