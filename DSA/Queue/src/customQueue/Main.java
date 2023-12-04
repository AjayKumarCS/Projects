package customQueue;
import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	static int inputInt(String s) {
		int input;
		while(true) {
			try {
				System.out.print(s);
				input = sc.nextInt();
			} catch(Exception e) {
				sc.nextLine();
				System.out.println("Please enter only digits");
				continue;
			}
			break;
		}
		return input;
	}

	public static void main(String[] args) {
	
		Queue queue = new Queue();
		
		System.out.println("Press 1 for Enqueue");
		System.out.println("Press 2 for Dequeue");
		System.out.println("Press 3 for Peek");
		System.out.println("Press 4 for Contains");
		System.out.println("Press 5 to Size");
		System.out.println("Press 6 for Center");
		System.out.println("Press 7 for Sort");
		System.out.println("Press 8 for Reverse");
		System.out.println("Press 9 for Iterator");
		System.out.println("Press 0 for Print");
		
		while(true) {
			int input = inputInt("Enter your input: ");
			switch(input) {
				case 1 : {
					int data = inputInt("Enter data: ");
					queue.enque(data);
					break;
				}
				case 2 : {
					queue.deque();
					break;
				}
				case 3 : {
					System.out.println(queue.peek());
					break;
				}
				case 4 : {
					int ele = inputInt("Input element to search: ");
					System.out.println(queue.contains(ele));
					break;
				}
				case 5 : {
					System.out.println(queue.size());
					break;
				}
				case 6 : {
					System.out.println(queue.center());
					break;
				}
				case 7 : {
					queue.sort();
					break;
				}
				case 8 : {
					queue.reverse();
					break;
				}
				case 9 : {
					System.out.println(queue.iterator());
					break;
				}
				case 0 : {
					queue.print();
					break;
				}
				default : {
					System.out.println("invalid input");
					continue;
				}
			}
			System.out.println("Press 'Y' to exit");
			char exit = sc.next().charAt(0);
			if(exit == 'Y') {
				break;
			}	
		}
	}
}
