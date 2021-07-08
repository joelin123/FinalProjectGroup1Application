package com.group1.springboot.elena.login.model;


import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group1.springboot.utils.SystemUtils;


@Entity
@Table(name = "CustomerInfo")
@Component("CustomerBean")
public class CustomerBean {
	
	@Id @Column(name = "customerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerID;
	
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
	
	@Column(name="accountStatus")
	String accountStatus;
	
	@Column(name="mimeType")
	String  mimeType;
	
	// 圖片
	@JsonIgnore
	@Column(name="customerPhoto")
	Blob customerPhoto;
	
	// 不要將下面這個作成資料庫欄位
	@Transient
	String pictureString;
	
	// 拿來取前端input file
	@Transient
	MultipartFile customerImage;
	


	public CustomerBean() {
	}
	
	public CustomerBean(Integer customerID, String customerName, String account, String password, String email, String birthday,
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
	



	public CustomerBean(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
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

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Blob getCustomerPhoto() {
		return customerPhoto;
	}

	public void setCustomerPhoto(Blob customerPhoto) {
		this.customerPhoto = customerPhoto;
	}
	//沒上傳圖片的使用者就會有預設照片了
	public String getPictureString() {
		return SystemUtils.blobToDataProtocol(mimeType, customerPhoto);
	}

	public void setPictureString(String pictureString) {
		this.pictureString = pictureString;
	}

	public MultipartFile getCustomerImage() {
		return customerImage;
	}

	public void setCustomerImage(MultipartFile customerImage) {
		this.customerImage = customerImage;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerBean [customerID=");
		builder.append(customerID);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", account=");
		builder.append(account);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", cellphone=");
		builder.append(cellphone);
		builder.append(", address=");
		builder.append(address);
		builder.append(", accountStatus=");
		builder.append(accountStatus);
		builder.append(", mimeType=");
		builder.append(mimeType);
		builder.append(", customerPhoto=");
		builder.append(customerPhoto);
		builder.append(", pictureString=");
		builder.append(pictureString);
		builder.append(", customerImage=");
		builder.append(customerImage);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
