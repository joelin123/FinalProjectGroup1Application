package com.group1.springboot.wade.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group1.springboot.wade.dao.CarDaoImpl;
import com.group1.springboot.wade.model.Car;



@Service
@Transactional
public class CarServiceImpl {
	
//	@Autowired
//	private CarRepository repo;
	
	@Autowired
	CarDaoImpl CarDao;
	
//	public void delete(long id) {
//		repo.deleteById(id);
//	}

	public void save(Car car) {
		CarDao.save(car);
	}
	
	public List<Car> findAll(){
		return CarDao.findAll();
	}
	public Car findById(Long id) {
		return CarDao.findById(id);
	}
	
	
	public List<Car> queryByName(String name) {
		return CarDao.queryByName(name);
	}
	
	public void update(Car car) {
		CarDao.update(car);
	}

	public List<Car> findByName(String rname) {
		
		return CarDao.findByName(rname);
	}
	
}
	/*
	public Map<String, Object> findAll(){
		return eventDao.findAll();
	}
	
	

	public Map<String, Object> queryByName(String rname) {
		return eventDao.queryByName(rname);
	}
 */