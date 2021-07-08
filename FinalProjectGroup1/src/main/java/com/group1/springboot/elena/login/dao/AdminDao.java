package com.group1.springboot.elena.login.dao;

import java.util.List;

import com.group1.springboot.elena.login.model.CustomerBean;


public interface AdminDao {

/////////-----------------------/////////

	public void addUsers(CustomerBean accountBean);

	public CustomerBean selectUser(Integer customerID);

	public boolean deleteUser(Integer customerID);

	public void updateUser(CustomerBean accountBean);

	public List<CustomerBean> selectUserAll();
}
