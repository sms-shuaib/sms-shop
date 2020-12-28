package com.example.smsshop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.smsshop.Entity.PurchasedProduct;

public interface PurchaseDao extends CrudRepository<PurchasedProduct, Integer> {
	
	@Query(value="SELECT * from purchased_product u where u.ship_id= :shipId", nativeQuery = true)
	public List<PurchasedProduct> getPurchaseData(@Param("shipId") int shipId);
	

}
