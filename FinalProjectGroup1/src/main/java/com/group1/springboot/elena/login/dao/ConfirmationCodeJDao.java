package com.group1.springboot.elena.login.dao;


import java.util.List;

import com.group1.springboot.elena.login.model.ConfirmationCodeBean;


public interface ConfirmationCodeJDao {


	List<ConfirmationCodeBean> getAllTypes();

	void updateConfirmationCode(ConfirmationCodeBean bean);

	ConfirmationCodeBean getBeanByName(String type);

}