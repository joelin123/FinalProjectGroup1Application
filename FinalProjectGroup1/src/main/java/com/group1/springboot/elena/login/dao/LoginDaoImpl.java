package com.group1.springboot.elena.login.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.springboot.elena.login.model.CustomerBean;

@Repository
//@Transactional
public class LoginDaoImpl implements LoginDao {

	@Autowired
	EntityManager em;
	
	@Autowired
	CustomerBean users;


	// 註冊
	@Override
	public void insertCustomer(CustomerBean accountBean) {
		em.persist(accountBean);
	}

	//管理者端搜尋全部使用者+搜尋筆數
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllCustomers() {
		Map<String, Object> map = new HashMap<>();
		String hql = "From CustomerInfo";
		List<CustomerBean> list = em.createQuery(hql).getResultList();
		map.put("size", list.size());
		map.put("list", list);
		return map;
	}

	@Override
	public CustomerBean getCustomerById(Integer customerID) {
		return em.find(CustomerBean.class, customerID);
	}

	@Override
	public CustomerBean getCustomerByAccount(String account) {
		//一定要寫try catch不然會報錯
		CustomerBean bean = null;
		
		String hql = "FROM CustomerBean cb where cb.account = :ACCOUNT";
		try {
		bean = em.createQuery(hql, CustomerBean.class)
				 .setParameter("ACCOUNT", account)
				 .getSingleResult();
		}catch(NoResultException e) {
			;
		}
		return bean;
	}

	@Override
	public CustomerBean getCustomerByCellphone(String cellphone) {
	CustomerBean bean = null;
		
		String hql = "FROM CustomerBean cb where cb.cellphone = :CELLPHONE";
		try {
		bean = em.createQuery(hql, CustomerBean.class)
				 .setParameter("CELLPHONE", cellphone)
				 .getSingleResult();
		}catch(NoResultException e) {
			;
		}
		return bean;
	}

	@Override
	public CustomerBean getCustomerByEmail(String email) {
	CustomerBean bean = null;
		
		String hql = "FROM CustomerBean cb where cb.email = :EMAIL";
		try {
		bean = em.createQuery(hql, CustomerBean.class)
				 .setParameter("EMAIL", email)
				 .getSingleResult();
		}catch(NoResultException e) {
			;
		}
		return bean;
	}


	@Override
	public boolean deleteCustomerById(Integer customerID) {
		CustomerBean bean = em.find(CustomerBean.class, customerID);
		em.remove(bean);
		return false;
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean accountBean) {
		return em.merge(accountBean);
	}

	@Override
	public boolean deleteCustomerByAccount(String account) {
		CustomerBean bean = em.find(CustomerBean.class, account);
		em.remove(bean);
		
		return false;
	}


}
