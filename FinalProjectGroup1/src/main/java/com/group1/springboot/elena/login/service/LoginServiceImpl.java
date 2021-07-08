package com.group1.springboot.elena.login.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.springboot.elena.login.dao.LoginDao;
import com.group1.springboot.elena.login.model.CustomerBean;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao logindao;

	@Autowired
	EntityManager em;

	@Override
	public CustomerBean checkLogin(CustomerBean users) {

		String hqlstr = "from CustomerBean where account=:ACCOUNT and password=:PASSWORD";
		Query<CustomerBean> query = (Query<CustomerBean>) em.createQuery(hqlstr, CustomerBean.class);
		
		//setParameter = 設定查詢語句(hqlstr) 裡面的參數
		query.setParameter("ACCOUNT", users.getAccount());
		query.setParameter("PASSWORD", users.getPassword());

		
		CustomerBean result = query.uniqueResult();
		if (result != null) {

			return result;
		}

		return null;

	}
	
	//帝凱says: 用這個較佳
	@Override
	public CustomerBean checkLogin(String account, String password) {
		// 直接透過getCustomerByAccount取得該會員所有資料
		CustomerBean cusInfo = logindao.getCustomerByAccount(account);

//		之後若有Account status 可加入cusInfo.getAccountStatus().equals("true")
		if (cusInfo != null && cusInfo.getPassword().equals(password)) {

			return cusInfo;
		}
		return null;
	}

	@Override
	public void insertCustomer(CustomerBean accountBean) {
		logindao.insertCustomer(accountBean);
	}

	@Override
	public Map<String, Object> getAllCustomers() {
		return logindao.getAllCustomers();
	}

	@Override
	public CustomerBean getCustomerById(Integer customerID) {
		return logindao.getCustomerById(customerID);
	}

	@Override
	public CustomerBean getCustomerByAccount(String account) {
		return logindao.getCustomerByAccount(account);
	}

	@Override
	public CustomerBean getCustomerByCellphone(String cellphone) {
		return logindao.getCustomerByCellphone(cellphone);
	}

	@Override
	public CustomerBean getCustomerByEmail(String email) {
		return logindao.getCustomerByEmail(email);
	}

	// 只有在service有的:邏輯
	@Override
	public boolean checkifpasswordisthesameornot(String useraccount, String password) {
		CustomerBean bean = logindao.getCustomerByAccount(useraccount);
		String oldpassword = bean.getPassword();
		if (password.equals(oldpassword)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean deleteCustomerById(Integer customerID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomerByAccount(String account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean accountBean) {
		return logindao.updateCustomer(accountBean);
	}

}
