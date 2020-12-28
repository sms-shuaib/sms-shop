package com.example.smsshop.Config;

import java.util.ArrayList;import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.smsshop.Dao.UserDao;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username != null && username!=""){
			 com.example.smsshop.Entity.User userDetail = userDao.getByUserName(username);
			 if(Objects.isNull(userDetail)) {
				 return null;
			 };
			return new User(userDetail.getUserName(),userDetail.getPassWord(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}