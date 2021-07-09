package com.group1.springboot.elena.login.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "CustomerInfo")
@Component("LoginBean")
public class LoginBean {
	
	@Id @Column(name = "customerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerID;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "customerAccount")
    private String account;
	
	@Column(name = "customerPassword")
    private String password;
	
	@Column(name = "customerEmail")
    private String email;
    
	@Column(name = "customerBirthday")
	private String birthday;
	
	@Column(name = "customerGender")
    private String gender;
	
	@Column(name = "customerCellphone")
    private String cellphone;
	
	@Column(name = "customerAddress")
    private String address;

	public LoginBean() {

	}
	
	

	public LoginBean(int customerID, String customerName, String account, String password, String email, String birthday,
			String gender, String cellphone, String address) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.account = account;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.cellphone = cellphone;
		this.address = address;
	}
	
	

	public LoginBean(String customerName, String account, String password, String email, String birthday, String gender,
			String cellphone, String address) {
		super();
		this.customerName = customerName;
		this.account = account;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.cellphone = cellphone;
		this.address = address;
	}



	public LoginBean(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
}
