package com.group1.springboot.elena.login.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseLoginController {
			
		@GetMapping("/elena")
		public String index() {
			return "Elena/login";
		}


}
