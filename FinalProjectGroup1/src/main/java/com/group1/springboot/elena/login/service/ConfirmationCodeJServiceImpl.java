package com.group1.springboot.elena.login.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.group1.springboot.elena.login.dao.ConfirmationCodeJDao;
import com.group1.springboot.elena.login.model.ConfirmationCodeBean;


@Service
@EnableTransactionManagement
public class ConfirmationCodeJServiceImpl implements ConfirmationCodeJService  {
	
	@Autowired
	ConfirmationCodeJDao cfcd;
	
	@Override
	@Transactional
	public List<ConfirmationCodeBean> getAllTypes(){
		return cfcd.getAllTypes();
	}
	 
	
	@Override
	@Transactional
	public ConfirmationCodeBean getBeanByName(String type) {
		return cfcd.getBeanByName(type);
	}
	
	@Override
	@Transactional
	public void updateConfirmationCode(ConfirmationCodeBean bean) {
		
		cfcd.updateConfirmationCode(bean);
	}


}
