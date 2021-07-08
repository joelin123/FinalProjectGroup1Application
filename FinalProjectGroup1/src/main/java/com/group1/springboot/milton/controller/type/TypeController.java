package com.group1.springboot.milton.controller.type;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group1.springboot.milton.service.TypeServiceImpl;

@Controller
public class TypeController {
 
 @Autowired
 TypeServiceImpl typeService;

 @Autowired
 ServletContext context;
 
// @GetMapping(value={"/findAllTypes","queryType"}, produces = "application/json; charset=UTF-8") 
// public @ResponseBody Map<String, Object> findAll(){
//  return typeService.findAll(); 
// }
 
 @GetMapping(value="/getAllTypes", produces = "application/json; charset=UTF-8") 
 public @ResponseBody Map<String, Object> findAll(){
  //傳回值會轉成JSON送回去
  return typeService.findAll(); 
 }
// produces-> 輸出是json
 @GetMapping(value={"/findAllTypes","queryType"}) 
 public String sendTypePage(){
  return "type/showAllTypes";
 }
}