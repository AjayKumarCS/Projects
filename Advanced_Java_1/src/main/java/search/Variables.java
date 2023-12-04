package search;

import java.util.Scanner;

public class Variables {
	public static Scanner sc = new Scanner(System.in);
	
	/* this interval is the time in which thread to load new files rerun */
	public static int interval = 15000; /* time in millisec */
	public static String csvPath = "D:\\";
	
	public static void setCsvPath() {
		System.out.print("\nEnter Path : ");
		String value = sc.nextLine();
		csvPath = value;
	}
}
