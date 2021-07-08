package com.group1.springboot.milton.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group1.springboot.milton.dao.PlaceDaoImpl;
import com.group1.springboot.milton.dao.TypeDaoImpl;
import com.group1.springboot.milton.model.Place;
import com.group1.springboot.milton.model.ProductInfo;
import com.group1.springboot.milton.model.RestaurantType;

@Service
@Transactional
public class PlaceServiceImpl {
	
	public PlaceServiceImpl() {
		System.out.println("---PlaceServiceImpl---------------------------------");
	}

	@Autowired
	PlaceDaoImpl placeDao;
	
	@Autowired
	TypeDaoImpl typeDao;
	
	
	
	public void save(Place place) {
		RestaurantType type = typeDao.findById(place.getType().getTypeId());
		place.setType(type);
		placeDao.save(place);
		
	}
	
	
	
	public Map<String, Object> findAll(){
		return placeDao.findAll();
	}
	
	public ProductInfo findById(Long id){
		return placeDao.findById(id);
	}
	
	public Map<String, Object> findByType(Integer typeId){
		return placeDao.findByType(typeId);
	}
	
	public void update(Place place) {
		placeDao.update(place);
	}

	public Map<String, Object> queryByName(String rname) {
		return placeDao.queryByName(rname);
	}
}
