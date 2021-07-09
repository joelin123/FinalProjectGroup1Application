package com.group1.springboot.sylvia.dao;


import java.util.List;


import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group1.springboot.sylvia.model.Event;



@Repository
public class EventDaoImpl {
	
	@Autowired 
	EntityManager em;
	
	public EventDaoImpl() {
		
	}

	public void save(Event event) {
		em.persist(event);
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> findAll(){
		String hql ="from Event";	
		return em.createQuery(hql).getResultList();	
	}
	
	//用ID找
	public Event findById(Long id){		
		return em.find(Event.class,id); 	
	}
	
	
	public List<Event>queryByName(String rname) {
        String hql ="from Event where name =:name";
		
        List<Event> list = em.createQuery(hql,Event.class)
		         .setParameter("name", "%"+rname+"%")
		         .getResultList();
        System.out.println(list.size()+","+list);
	return list;
	}
	
	//用name找
	/*
	@SuppressWarnings("unchecked")
	public List<Event> queryByName(String rname) {
		String hql ="from Event where name =:name";
		
		return em.createQuery(hql)
		         .setParameter("name", "%"+rname+"%").getResultList();		
		
		//修改
		
	}
	
	*/
	
	public void update(Event event) {
		em.merge(event);
	}

	public List<Event> findByName(String name) {
		String hql = "from Event e where e.name like :name ";
		
		List<Event> list = em.createQuery(hql, Event.class)
				             .setParameter("name",  "%" + name +"%")
				             .getResultList();
		System.out.println(list.size() + "," + list);
		return list;
	}
	
}
	/*------------------------------------------------
	//找全部
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> findAll(){
		Map<String,Object> map = new HashMap<>(); 
		String hql = "FROM Place";
		List<Event> list = em.createQuery(hql).getResultList();
		map.put("size",list.size());
		map.put("list",list);
		return map;
	}
	
	
	
	
	
	//用name找
	
	public Map<String , Object> queryByName(String rname ){
		Map<String , Object> map = new HashMap<>();
		String hql = "FROM EVENT e WHERE e.name like :name ";
		List<Event> list = em.createQuery(hql, Event.class)
				             .setParameter("name",  "%" + rname +"%")
				             .getResultList();
		map.put("size", list.size());
		map.put("list", list);
		return map;
		
	}
*/
