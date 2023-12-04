package search;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String pid;
	private String name;
	private String color;
	private String gender;
	private String size;
	private String available;
	private Float price;
	private Float rating;
	
	public Product() {}
	
	public Product(String[] details) {
		this.pid = (details[0]);
		this.name = details[1];
		this.color = details[2];
		this.gender = details[3];
		this.size = details[4];
		this.price = Float.parseFloat(details[5]);
		this.rating = Float.parseFloat(details[6]);
		this.available = details[7];
	}
	
	public String getDetails() {
		String s = "";
		s += "\nProduct ID: "+this.pid +"\nProduct Name: "+ this.name +"\nColour: "+ this.color +"\nGender: "+ this.gender +
				"\nSize: "+ this.size +"\nPrice: "+ this.price +"\nRating: "+ this.rating +"\nInstock: "+ this.available; 
		return s;
	}
}
