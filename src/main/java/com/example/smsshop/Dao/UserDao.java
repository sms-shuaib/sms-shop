package com.example.smsshop.Dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.smsshop.Entity.User;

public interface UserDao extends CrudRepository<User, Integer> {
	
	@Query(value="SELECT * from USER u where u.user_name= :userName", nativeQuery = true)
	public User getByUserName(@Param("userName") String userName);

}
