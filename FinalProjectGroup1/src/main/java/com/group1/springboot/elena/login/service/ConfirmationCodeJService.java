package com.group1.springboot.elena.login.service;


import java.util.List;

import com.group1.springboot.elena.login.model.ConfirmationCodeBean;


public interface ConfirmationCodeJService {

	List<ConfirmationCodeBean> getAllTypes();


	void updateConfirmationCode(ConfirmationCodeBean bean);

	ConfirmationCodeBean getBeanByName(String type);

}