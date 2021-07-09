package com.group1.springboot.sylvia.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group1.springboot.sylvia.dao.EventDaoImpl;
import com.group1.springboot.sylvia.model.Event;



@Service
@Transactional
public class EventServiceImpl {
	
	@Autowired
	EventDaoImpl eventDao;

	public void save(Event event) {
		eventDao.save(event);
	}
	
	public List<Event> findAll(){
		return eventDao.findAll();
	}
	public Event findById(Long id) {
		return eventDao.findById(id);
	}
	
	
	public List<Event> queryByName(String name) {
		return eventDao.queryByName(name);
	}
	
	public void update(Event event) {
		eventDao.update(event);
	}

	public List<Event> findByName(String rname) {
		
		return eventDao.findByName(rname);
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