package com.group1.springboot.milton.model;

public class Pet {
	Integer no;
	String name;
	String type;
	
	
	public Pet() {
		super();
	}


	public Pet(Integer no, String name, String type) {
		super();
		this.no = no;
		this.name = name;
		this.type = type;
	}
	
	
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
