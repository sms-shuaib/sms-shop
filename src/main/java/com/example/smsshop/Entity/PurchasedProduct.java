package com.example.smsshop.Entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name="PurchasedProduct")
public class PurchasedProduct {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ship_id")
	private int shipId;
	
	@Column(name="quantity_id")
	private int quantityId;
	
	@Column(name="product_id")
	private int productId;
	
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="title")
	private String title;
	
	@Column(name="placed_date")
	private Date placedDate;
	
	@Column(name = "price")
	private int price;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShipId() {
		return shipId;
	}

	public void setShipId(int shipId) {
		this.shipId = shipId;
	}

	public int getQuantityId() {
		return quantityId;
	}

	public void setQuantityId(int quantityId) {
		this.quantityId = quantityId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	

}
