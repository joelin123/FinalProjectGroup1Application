package com.group1.springboot.sylvia.model;

import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="EventV2")
public class EventItem {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Id;
	String Area;                 //地區
	
	Clob comment;                //購買須知
	
	@JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
	Date createDate;             //填表時間 yyyy-MM-dd
	
	Timestamp registerDate;      //yyyy-MM-dd HH:mm:ss
	
	

	public EventItem() {
		super();
	}



	public EventItem(Long id, String area, Clob comment, Date createDate, Timestamp registerDate) {
		super();
		Id = id;
		Area = area;
		this.comment = comment;
		this.createDate = createDate;
		this.registerDate = registerDate;
	}







	public Long getId() {
		return Id;
	}



	public void setId(Long id) {
		Id = id;
	}



	public String getArea() {
		return Area;
	}



	public void setArea(String area) {
		Area = area;
	}



	public Clob getComment() {
		return comment;
	}



	public void setComment(Clob comment) {
		this.comment = comment;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Timestamp getRegisterDate() {
		return registerDate;
	}



	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}
	
	
	
}
