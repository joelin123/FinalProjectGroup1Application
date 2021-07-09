package com.group1.springboot.sylvia.model;

import java.io.ByteArrayOutputStream;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Base64;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group1.springboot.sylvia.Utils.SystemUtils;


@Entity
@Table(name="EventV1")
public class Event {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;                     //編號	
	
	private String name;                 //名稱
	
	private Integer price;               //價格
	
	private Double longitude;             //經度
	
	private Double  latitude;             //緯度
	
	private String address;              //地址
	
	private String mimeType;

	
	private String content;                //內容
	
	@JsonIgnore
	private Blob picture;                //照片
  
	@Transient
	MultipartFile eventImage;
	
	@Transient
	String pictureString;
	
	
	public Event(Long id, String name, Integer price, Double longitude, Double latitude, String address,String content,
			Blob picture ,String mimeType) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.content = content;
		this.picture = picture;
		this.mimeType = mimeType;
	}


	public Event(Long id, String name, Integer price, Double longitude, Double latitude, String address,
			String mimeType, String content, Blob picture, MultipartFile eventImage, String pictureString) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.mimeType = mimeType;
		this.content = content;
		this.picture = picture;
		this.eventImage = eventImage;
		this.pictureString = pictureString;
	}


	//空的建構子,一定要
	public Event() {
		super();
	}
  
	

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	


	public Blob getPicture() {
		return picture;
	}


	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	
	
	
	
	public String getMimeType() {
		return mimeType;
	}


	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	


	public String getPictureString() {
		return SystemUtils.blobToDataProtocol(mimeType, picture);
	}



	public void setPictureString(String pictureString) {
		this.pictureString = pictureString;
	}


	public MultipartFile getEventImage() {
		return eventImage;
	}


	public void setEventImage(MultipartFile eventImage) {
		this.eventImage = eventImage;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", price=");
		builder.append(price);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", address=");
		builder.append(address);
		builder.append(", content=");
		builder.append(content);
		builder.append(", picture=");
		builder.append(picture);
		builder.append("]");
		return builder.toString();
	}

}


