package com.group1.springboot.elena.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseLoginController {
//首頁導到登入畫面的mapping
	
		@GetMapping("/elena")
		public String index() {
			return "Elena/login";
		}


}
