package customHashTable;
import java.util.*;

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
		
		int size = inputInt("Enter Size of HashTable: ");
		
		HashTable table = new HashTable(size);
		
		System.out.println("Press 1 for Insertion");
		System.out.println("Press 2 for Deletion");
		System.out.println("Press 3 for Contains");
		System.out.println("Press 4 to Get value by key");
		System.out.println("Press 5 to Size");
		System.out.println("Press 6 for Iterator");
		System.out.println("Press 7 for Print");
		
		while(true) {
			int input = inputInt("Enter your input: ");
			switch(input) {
				case 1 : {
					int value = inputInt("Enter value: ");
					int key = inputInt("Enter key: ");
					table.insert(key, value);
					break;
				}
				case 2 : {
					int key = inputInt("Enter key: ");
					table.delete(key);
					break;
				}
				case 3 : {
					int value = inputInt("Enter value: ");
					int key = inputInt("Enter key: ");
					System.out.println(table.contains(key, value));
					break;
				}
				case 4 : {
					int key = inputInt("Enter key: ");
					table.valueByKey(key);
					break;
				}
				case 5 : {
					System.out.println(table.size());
					break;
				}
				case 6 : {
					System.out.println(table.iterator());
					break;
				}
				case 7 : {
					table.print();
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
