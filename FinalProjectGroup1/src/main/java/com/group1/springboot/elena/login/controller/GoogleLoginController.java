package com.group1.springboot.elena.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GoogleLoginController {

	//不知道為何下面這個可以連到google認證
	@GetMapping("/googleLogin")
	public String hello() {
		return "Elena/login";
	}
	
	@GetMapping("/restricted")
	public String restricted() {
		return "after logged in";
	}
	
	
	
	
}
