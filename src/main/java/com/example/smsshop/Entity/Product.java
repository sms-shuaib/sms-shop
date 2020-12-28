package com.example.smsshop.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="price")
	private int price;
	
	@Column(name="category")
	private String category;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="quantity_id")
	private int quantityId;
	
	

	public Product() {
		
	}

	public Product(int id,String title, int price, String category, String imageUrl, int quantityId) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imageUrl = imageUrl;
		this.quantityId = quantityId;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public int getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(int quantityId) {
		this.quantityId = quantityId;
	}
	
	
	

}
