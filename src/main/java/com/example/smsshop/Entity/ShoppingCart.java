package com.example.smsshop.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name="SHOPPINGCART")
public class ShoppingCart {
	
	@Id
	@Column(name="shop_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="quantity_id")
	private int quantityId;
	
	@Column(name="product_id")
	private int productId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	

}
