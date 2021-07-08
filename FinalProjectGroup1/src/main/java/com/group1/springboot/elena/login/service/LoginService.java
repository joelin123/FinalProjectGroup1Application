package com.group1.springboot.elena.login.service;

import java.util.Map;

import com.group1.springboot.elena.login.model.CustomerBean;

public interface LoginService {
	
	
	public CustomerBean checkLogin(CustomerBean users);
	
	public CustomerBean checkLogin(String account, String password);
	
	//會員註冊
	public void insertCustomer(CustomerBean accountBean);
	
	//	管理員後台撈全部使用者
	Map<String, Object> getAllCustomers();
	
	public CustomerBean getCustomerById(Integer customerID);
	
	//下面這個是登入後撈資料用，還有確認帳號重複用(下面四個) 給ajax用
	public CustomerBean getCustomerByAccount(String account);
	
	public CustomerBean getCustomerByCellphone(String cellphone);
	
	public CustomerBean getCustomerByEmail(String email);
	
	
	public boolean deleteCustomerById(Integer customerID);
	
	public boolean deleteCustomerByAccount(String account);
	
	public CustomerBean updateCustomer(CustomerBean accountBean);
	
	// crud之外的都放在service
	boolean checkifpasswordisthesameornot(String useraccount,String password);
}
