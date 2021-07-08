package com.group1.springboot.milton.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.springboot.milton.model.ProductInfo;
import com.group1.springboot.milton.model.ProductType;
import com.group1.springboot.milton.model.RestaurantType;

@Repository
public class TypeDaoImpl {
	@Autowired
	EntityManager em;
	
	public TypeDaoImpl() {
	}
	
	public List<ProductType> findRestaurantTypes() {
		String hql = "FROM ProductType";
		List<ProductType> list = em.createQuery(hql, ProductType.class).getResultList();
		
		return list;
	}
	
	public List<ProductInfo> findProducts() {
		String hql = "FROM ProductInfo"; 
		List<ProductInfo> list = em.createQuery(hql, ProductInfo.class).getResultList();
		System.out.println(list);
		return list;
	}
	
	public RestaurantType  findById(Integer id) {
		return em.find(RestaurantType.class, id);
	}

	public void save(RestaurantType type) {
		em.persist(type);
	}

	public Map<String, Object> findAll() {
		String hql = "FROM RestaurantType";
		Map<String, Object> map = new HashMap<>();
		try {
			List<RestaurantType> list = em.createQuery(hql, RestaurantType.class).getResultList();
			map.put("size", list.size());
			map.put("types", list);
			
		} catch(Exception e) {
			map.put("error", e.getMessage());
		}
		return map;
	}


	
}
