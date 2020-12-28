package com.example.smsshop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.smsshop.Entity.Product;

public interface ProductDao extends CrudRepository<Product, Integer>{
	
	@Query(value = "SELECT * from PRODUCT p WHERE p.category = :value" , nativeQuery = true)
	public List<Product> getByCategory(@Param("value") String value);
	
	
	@Query(value="SELECT * from PRODUCT p where p.quantity_id >= 1" , nativeQuery = true)
	public List<Product> getProductByQuantity();

}
