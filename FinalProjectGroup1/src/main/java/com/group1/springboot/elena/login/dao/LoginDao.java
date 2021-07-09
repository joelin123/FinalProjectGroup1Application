package com.group1.springboot.elena.login.dao;


import org.springframework.stereotype.Repository;

import com.group1.springboot.elena.login.model.LoginBean;


@Repository
public interface LoginDao {
	
	public int checkLogin(LoginBean users);

	public void insert(LoginBean accountBean);
	
	public LoginBean select(int customerID);
	
	public boolean delete(int customerID);
	
	public LoginBean update(LoginBean accountBean);
	
}
