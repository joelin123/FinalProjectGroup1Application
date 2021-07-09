package com.group1.springboot.elena.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.group1.springboot.elena.login.model.LoginBean;
import com.group1.springboot.elena.login.service.LoginService;


@RestController 
@Controller
//@RequestMapping("/elena")
@SessionAttributes(names = {"user","id"})
public class LoginAjaxController {

	@Autowired
	private LoginService service;
	

	@PostMapping(path = "/elena/ajaxregister.controller")
	public LoginBean actionAdduser(
			@RequestBody LoginBean accbean, Model m) {
			service.insert(accbean);
			
			return accbean;
		
}
	

}

