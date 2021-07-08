package com.group1.springboot.elena.login.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ConfirmationCode")
@Table(name = "ConfirmationCode")
public class ConfirmationCodeBean {

	@Id
	@Column(name = "typeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer typeId;
 
	
	@Column(name = "typeName")
	String typeName;
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	@Column(name = "confirmationCode")
	String confirmationCode;

	public ConfirmationCodeBean() {

	}

	public ConfirmationCodeBean(String confirmationCode) {
		this.confirmationCode=confirmationCode;
	}
	
	public ConfirmationCodeBean(String confirmationCode, String typeName) {
		this.typeName=typeName;
		this.confirmationCode=confirmationCode;
	}
	
	public ConfirmationCodeBean(Integer typeId,String confirmationCode, String typeName) {
		this.typeId = typeId;
		this.typeName=typeName;
		this.confirmationCode=confirmationCode;
	}
	
	
	
	
}
