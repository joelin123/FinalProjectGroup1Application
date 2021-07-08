package com.group1.springboot.milton.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductInfo")
public class ProductInfo {
	
	@ManyToOne()
	@JoinColumn(name="product_typeId")
	ProductType  type;
	
	
    // 多方	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer   productid;
	String productname;
	Integer	productprice;
	String typename;
	
	
	
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Integer getProductprice() {
		return productprice;
	}
	public void setProductprice(Integer productprice) {
		this.productprice = productprice;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
	public ProductInfo() {
		
	}
	
	public ProductInfo(  Integer productid, String productname, Integer productprice, String typename) {
		super();
		
		this.productid = productid;
		this.productname = productname;
		this.productprice = productprice;
		this.typename = typename;
	}
	
	
	
	
	
	
	
	
	
}
