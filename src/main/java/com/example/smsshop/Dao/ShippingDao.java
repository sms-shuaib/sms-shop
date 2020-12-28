package com.example.smsshop.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.smsshop.Entity.ShippingDetail;

public interface ShippingDao extends CrudRepository<ShippingDetail, Integer> {

}
