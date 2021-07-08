package com.group1.springboot.milton.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ProductType")
public class ProductType {
	
	@OneToMany(mappedBy="type")
	Set<Place>  products = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer   typeId;
	String    typeName;

	public ProductType() {
	}
	
	public ProductType(Set<Place> products, Integer typeId, String typeName) {
		super();
		this.products = products;
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public Set<Place> getProducts() {
		return products;
	}

	public void setProducts(Set<Place> products) {
		this.products = products;
	}

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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductType [placeId=");
		builder.append(placeId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", address=");
		builder.append(address);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", link=");
		builder.append(link);
		builder.append(", hashCode=]" + hashCode());
		return builder.toString();
	}

	
	
	
}
