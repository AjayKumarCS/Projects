package search;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReaderBuilder;

public class Inputs {
	public static Scanner sc = new Scanner(System.in);
	
	public static void fileLoader() {
		System.out.println("\nPlease enter folder location of csv files");
		Variables.setCsvPath();

		Main.files = new File(Variables.csvPath).list(Main.filter);
		while (Main.files == null || Main.files.length == 0) {
				System.out.println("\nNo csv files found!");
				System.out.println("\nTry changing folder location of csv files");
				Variables.setCsvPath();
				Main.files = new File(Variables.csvPath).list(Main.filter);
		}
		try {
			for (int i = 0; i < Main.files.length; i++) {
				Main.reader = new CSVReaderBuilder(new FileReader(Variables.csvPath + "\\" + Main.files[i])).withSkipLines(1).withCSVParser(Main.parser).build();
				String[] nextLine;
				while ((nextLine = Main.reader.readNext()) != null) {
					Main.list.add(new Product(nextLine));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private static String[] getColorList(List<Product> list) {		
		HashMap<String,String> availableColors = new HashMap<String,String>();
		for(Product p: list) {
			availableColors.put(p.color, p.color);
		}
		return availableColors.keySet().toArray(new String[0]);
	}
	
	public static String getColorInput(List<Product> list) {
		String[] allColors = getColorList(list);
		String usrInp, value;
		
		while(allColors.length == 0) {
			System.out.println("\nCsv files are not formated properly!");
			fileLoader();
			allColors = getColorList(list);
		}
		
		System.out.println("\nAvailable Colours :");
		for(int i=1; i<=allColors.length; i++) {
			System.out.println(i +". "+ allColors[i-1]);
		}
		
		boolean test = false;
		while(true) {
			System.out.print("\nEnter colour: ");
			usrInp = sc.nextLine().trim();
			value = usrInp.substring(0, 1).toUpperCase() + usrInp.substring(1).toLowerCase();
			test = Arrays.asList(allColors).contains(value);
			if(test) {
				break;
			} else {
				System.out.println("Enter the colour from the given list only!");
			}
		}
		return value;
	}
	
	public static String getSizeInput() {
		String value;
		System.out.print("\nEnter size from:\nS\tM\tL\tXL\tXXL \n");
		System.out.print("\nSize: ");
		value = sc.nextLine().trim().toUpperCase();
		switch(value) {
		case "S" : break;
		case "M" : break;
		case "L" : break;
		case "XL" : break;
		case "XXL" : break;
		default : 
			System.out.println("Enter the size from the given list only!");
			return getSizeInput();
		}
		return value;
	}
	
	public static String getGenderInput() {
		String value;
		System.out.print("\nEnter Gender :\nM for Male\nF for Female \n");
		System.out.print("\nGender: ");
		value = sc.nextLine().trim().toUpperCase();
		switch(value) {
		case "M" : break;
		case "F" : break;
		default : 
			System.out.println("Enter Gender in correct format only!");
			return getGenderInput();
		}
		return value;
	}
	
	public static String getSortingOrder() {
		String value;
		System.out.print("\nEnter Output Preference From:\nPrice\nRating \nBoth \n");
		System.out.print("\nOutput Preference: ");
		value = sc.nextLine().trim().toLowerCase();
		switch(value) {
		case "price" : break;
		case "rating" : break;
		case "both" : 
			value = "forBoth";
			break;
		default : 
			System.out.println("Enter Output Preference in correct format only!");
			return getSortingOrder();
		}
		return value;
	}
	
	public static Boolean yListner() {
		return ((sc.nextLine().trim().toUpperCase().equals("Y"))? true : false);
	}
	
}
