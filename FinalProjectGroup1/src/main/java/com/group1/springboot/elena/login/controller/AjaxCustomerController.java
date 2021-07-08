package com.group1.springboot.elena.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group1.springboot.elena.login.model.CustomerBean;
import com.group1.springboot.elena.login.service.LoginService;

@Controller
public class AjaxCustomerController {
	//此controller為註冊時驗證帳號、手機、email是否重複用
	
	@Autowired
	LoginService loginService;	
	
	
	@PostMapping(value="/Ajax/Elena/checkduplicateaccount")
	public @ResponseBody Integer CheckDuplicateAccount(@RequestParam("account") String inputaccount) {
		Integer accountexistsornot=3;
		
		CustomerBean bean = loginService.getCustomerByAccount(inputaccount);
		if(bean!=null) {
			accountexistsornot = 0; //有帳號，不能用
		}else {
			accountexistsornot = 1; //沒有帳號，可以註冊
		}
		
		return accountexistsornot;
	}
	
	@PostMapping(value="/Ajax/Elena/checkduplicatecellphone")
	public @ResponseBody Integer CheckDuplicateCellphone(@RequestParam("cellphone") String inputcellphone) {
		Integer cellphoneexistsornot=3;
		
		CustomerBean bean = loginService.getCustomerByCellphone(inputcellphone);
		if(bean!=null) {
			cellphoneexistsornot = 0; //重複，不能用
		}else {
			cellphoneexistsornot = 1;
		}
		
		return cellphoneexistsornot;
	}
	
	@PostMapping(value="/Ajax/Elena/checkduplicateemail")
	public @ResponseBody Integer CheckDuplicateEmail(@RequestParam("email") String inputemail) {
		Integer emailexistsornot=3;
		
		CustomerBean bean = loginService.getCustomerByEmail(inputemail);
		if(bean!=null) {
			emailexistsornot = 0; //重複，不能用
		}else {
			emailexistsornot = 1;
		}
		
		return emailexistsornot;
	}

	@PostMapping(value="/Ajax/Elena/checksamepassword")
	public @ResponseBody Integer CheckSamePassword(
			@RequestParam("password") String inputpassword,
			@RequestParam("account") String useraccount
			) {
		Integer passwordisthesameornot=3;
		
		boolean validnewpwdornot = loginService.checkifpasswordisthesameornot(useraccount, inputpassword);
		if(validnewpwdornot == false) {
			passwordisthesameornot = 0; //重複，不能用
		}else {
			passwordisthesameornot = 1;
		}
		
		return passwordisthesameornot;
	}
	
	
	
	
	
}
