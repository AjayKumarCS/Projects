package com.ajay.entity;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "pid")
	private String pid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "size")
	private String size;
	
	@Column(name = "available")
	private String available;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "rating")
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
	
	public List<String> getDetails() {
		List<String> list = new ArrayList<>();
		Collections.addAll(list, this.pid, this.name, this.color, this.gender, this.size, this.price.toString(), this.rating.toString(), this.available); 
		return list;
	}
}

