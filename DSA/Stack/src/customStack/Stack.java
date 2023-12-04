package customStack;
import java.util.*;

public class Stack {
	
	int top;
	int size;
	int arr[];
	public Stack(int size) {
		this.size = size;
		top = -1;
		arr = new int[size];
	}
	
	private boolean isFull() {
		return top == size-1;
	}
	
	private boolean isEmpty() {
		return top == -1;
	}
	
	public void push(int data) {
		if(isFull()) {
			System.out.println("Stack is full");
		} else {
			arr[++top] = data;
		}
	}
	
	public void pop() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
		} else {
			top--;
		}
	}
	
    public void peek() {
    	if(isEmpty()) {
			System.out.println("Stack is empty");
    	} else {    		
    		System.out.println(arr[top]);
    	}
	}
    
    public Boolean contains(int value) {
    	for(int i = top; i >= 0; i--) {
    		if(arr[i] == value) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public int size() {
    	return top+1;
    }
    
	public int center() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
			return -1;
		} else {
			int mid = ((top+1 % 2 == 0) ? top+1 / 2 : (top+1 / 2) + 1);
			return arr[mid];
		}
	}
	
	public void sort() {  
		for (int i = 1; i < size(); i++) {  
			int j = i;  
			int a = arr[i];  
			while ((j > 0) && (arr[j-1] > a)) {  
				arr[j] = arr[j-1];  
				j--;  
			}  
			arr[j] = a;  
		}  
	}   
	
	public void reverse() {
		for (int i = 0; i < (top + 1) / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[top - i];
            arr[top - i] = temp;
        }
	}
	
	public ArrayList<Integer> iterator() {
		ArrayList<Integer> iterator = new ArrayList<>();
		for(int i = 0; i <= top; i++) {
			iterator.add(arr[i]);
		}
		return iterator;
	}
	
	public void print() {
		if(isEmpty()) {
			System.out.println("Stack is empty");
		} else {
			for (int i = 0; i <= top; i++) {
				System.out.print(arr[i] + " ");
			}	
			System.out.println();
		}
	}
	
}
