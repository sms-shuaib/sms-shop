package com.example.smsshop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.smsshop.Entity.ShoppingCart;

public interface ShoppingDao extends CrudRepository<ShoppingCart, Integer> {

	@Query(value="SELECT * from SHOPPINGCART s where s.product_id= :productId", nativeQuery = true)
	public ShoppingCart getByProductId(@Param("productId") int productId);
}
