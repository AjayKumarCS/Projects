package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductSearch {
	
	public static void search(List<Product> list) {
		String color, size, gender;
		
		while(true) {
			List<Product> result = new ArrayList<Product>();
			color = Inputs.getColorInput(list);
			size = Inputs.getSizeInput();
			gender = Inputs.getGenderInput();
			
			try {
				for(Product p: list) {
					if(p.check(color, size, gender) && p.isAvailable()) {
						result.add(p);
					}
				}
				
				Comparator<Product> forBoth = new Comparator<Product>() {
					@Override
				    public int compare(Product p1, Product p2) {
				       if(!p1.getPrice().equals(p2.getPrice())) {
				          return p1.getPrice().compareTo(p2.getPrice());
				       } else {
				          return p2.getRating().compareTo(p1.getRating());
				        }
				    }
				};
				  
				Comparator<Product> price = new Comparator<Product>() {
					@Override
					public int compare(Product p1, Product p2) {
						return p1.getPrice().compareTo(p2.getPrice());
					}
				};
				  
				Comparator<Product> rating = new Comparator<Product>() {
					@Override
					public int compare(Product p1, Product p2) {
						return p2.getRating().compareTo(p1.getRating());
					}
				};
				
				String sortOrder = Inputs.getSortingOrder();
				switch(sortOrder) {
					case "price": 
						Collections.sort(result,price);
						break;
					case "rating": 
						Collections.sort(result,rating);
						break;
					case "forBoth": 
						Collections.sort(result,forBoth);
						break;
				}
				if(!result.isEmpty()) {
					for(Product p: result) {
						System.out.println(p.getDetails());
					}
				} else {
					System.out.println("\nNo matching T-Shirt found, try changing colour or size");
					System.out.print("\nOr, you can try changing folder location of csv files by pressing 'y': ");
					if(Inputs.yListner()) {
						Inputs.fileLoader();
					}
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			System.out.println("\nDo you want to exit? ");
			System.out.print("\nPress y for yes: ");
			if(Inputs.yListner()) {
				break;
			}
		}
	}
}
