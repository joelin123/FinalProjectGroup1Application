package com.group1.springboot.elena.login.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.springboot.elena.login.model.ConfirmationCodeBean;


@Repository
public class ConfirmationCodeJDaoImpl implements ConfirmationCodeJDao {
	
	@Autowired
	EntityManager factory;


	@Override
	public ConfirmationCodeBean getBeanByName(String type) {
		
		ConfirmationCodeBean bean = null;
		String hql = "FROM ConfirmationCode as cc where cc.typeName = :TYPE";
		
		try {
		bean= (ConfirmationCodeBean) factory.createQuery(hql)
		       .setParameter("TYPE", type)
		       .getSingleResult();
		}catch(NoResultException e) {
			;
		}
		
		return bean;
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<ConfirmationCodeBean> getAllTypes(){
		String hql = "FROM ConfirmationCode";
		List<ConfirmationCodeBean> list = new ArrayList<>();
		list = factory.createQuery(hql).getResultList();
		return list;
		
	}
	
	@Override
	public void updateConfirmationCode(ConfirmationCodeBean bean) {
		factory.merge(bean);
		System.out.println("dao有做事");

	} 
	
}
