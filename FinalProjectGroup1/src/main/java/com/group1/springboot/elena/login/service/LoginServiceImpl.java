package com.group1.springboot.elena.login.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.springboot.elena.login.dao.LoginDao;
import com.group1.springboot.elena.login.model.LoginBean;



@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao logindao;
	
	@Override
	public int checkLogin(LoginBean users) {
		return logindao.checkLogin(users);
	}

	@Override
	public void insert(LoginBean accountBean) {
		logindao.insert(accountBean);
	}

	@Override
	public LoginBean select(int customerID) {
		return logindao.select(customerID);
	}

	@Override
	public boolean delete(int customerID) {
		return logindao.delete(customerID);
	}

	@Override
	public LoginBean update(LoginBean accountBean) {
		return logindao.update(accountBean);
	}

}
