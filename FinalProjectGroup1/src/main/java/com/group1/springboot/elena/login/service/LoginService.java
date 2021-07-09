package com.group1.springboot.elena.login.service;

import com.group1.springboot.elena.login.model.LoginBean;

public interface LoginService {
	
	public int checkLogin(LoginBean users);
	
	public void insert(LoginBean accountBean);
	
	public LoginBean select(int customerID);
	
	public boolean delete(int customerID);
	
	public LoginBean update(LoginBean accountBean);

}
