package search;

public class Product {
	String id, name, color, gender, size, available;
	Float price, rating;
	public Product(String[] details) {
		this.id = details[0];
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
		s += "\nProduct ID: "+this.id +"\nProduct Name: "+ this.name +"\nColour: "+ this.color +"\nGender: "+ this.gender +
				"\nSize: "+ this.size +"\nPrice: "+ this.price +"\nRating: "+ this.rating +"\nInstock: "+ this.available; 
		return s;
	}
	
	public Boolean check(String color, String size, String gender) {
		if(this.color.equals(color) && this.size.equals(size) && (this.gender.equals("U") || this.gender.equals(gender))) {
			return true;
		}
		return false;
	}
	
	public Boolean isAvailable() {
		return ((this.available.equals("Y")) ? true : false);
	}
	
	public Float getPrice() {
		return this.price;
	}
	
	public Float getRating() {
		return this.rating;
	}
}
