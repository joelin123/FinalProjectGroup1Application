package com.group1.springboot.milton.model;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.group1.springboot.utils.SystemUtils;

@Entity
@Table(name="Place_Spring")
public class Place {
	
	@ManyToOne
	@JoinColumn(name="typeId_0623")
	RestaurantType  type;
	
	
	
    // 多方	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long    placeId;
	Integer tId;
	String  name;
	String  phone;
	String  address;
	Double  longitude;
	Double  latitude;
	String  link;   
	String  mimeType;   
	
	@JsonIgnore
	Clob  	comment;
	
	@JsonIgnore
	Blob  	picture;
	
	@Transient   // 短暫. 臨時    Persistence: 永續儲存
	String  pictureString;    // data:image/gif;base64,.....
	
	@Transient
	MultipartFile placeImage;
	
	
	public RestaurantType getType() {
		return type;
	}

	public void setType(RestaurantType type) {
		this.type = type;
	}

	public MultipartFile getPlaceImage() {
		return placeImage;
	}

	public void setPlaceImage(MultipartFile placeImage) {
		this.placeImage = placeImage;
	}

	public Place() {
	}
	
	public Place(Integer  typeId, String name, String phone, String address, Double longitude, 
			     Double latitude, String link, Blob blob, Clob clob, String mimeType) {
		super();
		this.tId = typeId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.link = link;
		this.picture = blob;
		this.comment = clob;
		this.mimeType = mimeType;
	}
	
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Integer getTypeId() {   // typeId
		return tId;
	}

	public void setTypeId(Integer typeId) {
		this.tId = typeId;
	}

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}

	public Blob getPicture() {
		return picture;
	}

	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public Long getPlaceId() {
		return placeId;
	}
	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPictureString() {
		return SystemUtils.blobToDataProtocol(mimeType, picture);
	}

	public void setPictureString(String pictureString) {
		this.pictureString = pictureString;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Place [placeId=");
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
