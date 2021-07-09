package com.group1.springboot.elena.login.dao;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.springboot.elena.login.model.LoginBean;




@Repository
//@Transactional
public class LoginDaoImpl implements LoginDao {

	@Autowired
	EntityManager session;
	
	
	
	@Override
	public int checkLogin(LoginBean users) {
		
//		Session session = factory.getCurrentSession();
		
		String hqlstr = "from LoginBean where account=:account and password=:password";
		Query<LoginBean> query = (Query<LoginBean>) session.createQuery(hqlstr, LoginBean.class);
		
		query.setParameter("account", users.getAccount());
		query.setParameter("password", users.getPassword());
		
		LoginBean result = query.uniqueResult();
		if(result!=null) {
			
			return result.getCustomerID();
		}
		
		return -1;
	}

	@Override
	public void insert(LoginBean accountBean) {
//		Session session = factory.getCurrentSession();
//		LoginBean resultBean = session.get(LoginBean.class, accountBean.getCustomerID()); // 
		session.persist(accountBean);
//		if (resultBean == null) {
//			session.save(accountBean);
//			return accountBean;
		
	}
	

	@Override
	public LoginBean select(int customerID) {
		Session session = factory.getCurrentSession();
		return session.get(LoginBean.class, customerID);
	}

	@Override
	public boolean delete(int customerID) {
		Session session = factory.getCurrentSession();
		LoginBean resultBean = session.get(LoginBean.class, customerID);
		if (resultBean != null) {
			session.delete(resultBean);
			return true;
		}
		return false;
	}

	@Override
	public LoginBean update(LoginBean accountBean) {
		
		Session session = factory.getCurrentSession();
		LoginBean resultBean = session.get(LoginBean.class, accountBean.getCustomerID());
		if (resultBean != null) {
			
			resultBean.setCustomerName(accountBean.getCustomerName());
			resultBean.setAccount(accountBean.getAccount());
			resultBean.setPassword(accountBean.getPassword());
			resultBean.setEmail(accountBean.getEmail());
			resultBean.setBirthday(accountBean.getBirthday());
			resultBean.setGender(accountBean.getGender());
			resultBean.setCellphone(accountBean.getCellphone());
			resultBean.setAddress(accountBean.getAddress());
			
		}
		return resultBean;
	}

}
