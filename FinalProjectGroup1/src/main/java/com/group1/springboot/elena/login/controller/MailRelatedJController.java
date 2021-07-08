package com.group1.springboot.elena.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group1.springboot.elena.login.model.CustomerBean;
import com.group1.springboot.elena.login.service.JavaMailJService;
import com.group1.springboot.elena.login.service.LoginService;


@Controller
@RequestMapping("/elena/mail")
public class MailRelatedJController {

	@Autowired
	JavaMailJService mailService;
	
	@Autowired
	LoginService accountService;

	@GetMapping("/mailconfirmation/{code}")
	public String finishRegistration(@PathVariable("code") int code) {
	   int realId = ((code+499)/263-67)/157;
	   
	   CustomerBean userbean = (CustomerBean)accountService.getCustomerById(realId);
	   userbean.setAccountStatus("1");
	   accountService.updateCustomer(userbean);
	   
	   return "redirect:/";
	}

}
