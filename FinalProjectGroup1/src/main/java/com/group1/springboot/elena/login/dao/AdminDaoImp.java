package com.group1.springboot.elena.login.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.springboot.elena.login.model.CustomerBean;


@Repository
//@Transactional
public class AdminDaoImp implements AdminDao {

	@Autowired
	EntityManager em;

	@Autowired
	CustomerBean users;

	@Override
	public void addUsers(CustomerBean accountBean) {

		em.persist(accountBean);

	}

	@Override
	public CustomerBean selectUser(Integer customerID) {

		return em.find(CustomerBean.class, customerID);
	}

	@Override
	public boolean deleteUser(Integer customerID) {
		CustomerBean bean = em.find(CustomerBean.class, customerID);
		em.remove(bean);

		return false;
	}

	@Override
	public void updateUser(CustomerBean accountBean) {

		em.merge(accountBean);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerBean> selectUserAll() {

		String hql = "From CustomerBean";

		List<CustomerBean> list = em.createQuery(hql).getResultList();

		return list;
	}

}
