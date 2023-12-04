package customLinkedList;
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
		
		LinkedList list = new LinkedList();
		
		System.out.println("Press 1 for Insertion");
		System.out.println("Press 2 for Deletion");
		System.out.println("Press 3 for Center Element");
		System.out.println("Press 4 to Sort");
		System.out.println("Press 5 to Reverse");
		System.out.println("Press 6 for Size");
		System.out.println("Press 7 for Iteration");
		System.out.println("Press 8 for Print");
		
		while(true) {
			int input = inputInt("Enter your input: ");
			switch(input) {
				case 1 : {
					System.out.println("Select Insertion type:");
					System.out.println("Press 1 for Insert");
					System.out.println("Press 2 for Insert at index");
					int type = inputInt("");
					switch(type) {
						case 1 : {
							int data = inputInt("Enter the data: ");
							list.insert(data);
							break;
						}
						case 2 : {
							int data = inputInt("Enter the data: ");
							int index = inputInt("Enter index: ");
							list.insertAtIndex(data,index);
						}
					}
					break;
				}
				case 2 : {
					System.out.println("Select Deletion type:");
					System.out.println("Press 1 for Delete");
					System.out.println("Press 2 for Delete at index");
					int type = inputInt("");
					switch(type) {
						case 1 : {
							list.delete();
							break;
						}
						case 2 : {
							int index = inputInt("Enter index: ");
							list.deleteAtIndex(index);
						}
					}
					break;
				}
				case 3 : {
					System.out.println(list.center());
					break;
				}
				case 4 : {
					list.sort();
					break;
				}
				case 5 : {
					list.reverse();
					break;
				}
				case 6 : {
					System.out.println(list.size());
					break;
				}
				case 7 : {
					System.out.println(list.iterator());
					break;
				}
				case 8 : {
					list.print();
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
