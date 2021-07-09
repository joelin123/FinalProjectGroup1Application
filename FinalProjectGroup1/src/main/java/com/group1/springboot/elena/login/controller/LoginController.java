package com.group1.springboot.elena.login.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.group1.springboot.elena.login.model.LoginBean;
import com.group1.springboot.elena.login.service.LoginService;



@Controller
@EnableTransactionManagement
@RequestMapping("/elena")
@SessionAttributes(names = {"user","id"})		
//@SessionAttributes(name=....)
public class LoginController {
	
	@Autowired
	private LoginService service;
	@Autowired
	private LoginBean Accbean;
	
	@RequestMapping(path = "/loginMainPage.controller", method = RequestMethod.GET)
	public String processLoginMainPage() {
		return "Elena/login"; 
	}
	
	@RequestMapping(path = "/registerForm.do", method = RequestMethod.GET)
	public String processRegisterForm() {
		return "Elena/AjaxRegisterForm";
	}
	
	@RequestMapping(path = "/updateForm.do", method = RequestMethod.GET)
	public String processUpdateForm(Model m) {
		int id=  (int) m.getAttribute("id");
		LoginBean bean = service.select(id);
		fill(m, bean);
		return "Elena/UpdateCustomerForm";
	}
	
	@RequestMapping(path = "/deleteAccount.do", method = RequestMethod.GET)
	public String processDeleteAccount(Model m) {
		int id=  (int) m.getAttribute("id");
		service.delete(id);
		
		return "Elena/login";
	}
	
	@RequestMapping(path = "/update.controllerweb", method = RequestMethod.POST)
	public String processUpdateForm(
		@RequestParam(name = "customerName", required = false) String customerName, 
		@RequestParam(name = "account", required = false) String account, 
		@RequestParam(name = "password", required = false) String password,
		@RequestParam(name = "email", required = false) String email, 
		@RequestParam(name = "birthday", required = false) String birthday, 
		@RequestParam(name = "gender", required = false) String gender, 
		@RequestParam(name = "cellphone", required = false) String cellphone, 
		@RequestParam(name = "address", required = false) String address, Model m) {
	{
		int uid=  (int) m.getAttribute("id");
		LoginBean cus = service.select(uid);
	 
	if (customerName != null && customerName.length() > 0) {
		cus.setCustomerName(customerName);
	}
	if (account != null && account.length() > 0) {
//		&& account.length() > 0
		cus.setAccount(account);
	}
	if (password != null && password.length() > 0) {
		cus.setPassword(password);
	}
	if (email != null && email.length() > 0) {
		cus.setEmail(email);
	}
	if (birthday != null && birthday.length() > 0) {
		cus.setBirthday(birthday);
	}
	if (gender != null) {
		cus.setGender(gender);
	}
	if (cellphone != null && cellphone.length() > 0) {
		cus.setCellphone(cellphone);
	}
	if (address != null && address.length() > 0) {
		cus.setAddress(address);
	}
		service.update(cus);		
		
		fill(m, cus);
		return "Elena/UpdateCustomerForm";
	}
	}
	@PostMapping(path = "/register.controller")
	public String actionAdduser(
			@RequestParam(name = "customerName") String name, 
			@RequestParam(name = "account") String account, 
			@RequestParam(name = "password") String password,
			@RequestParam(name = "email") String email, 
			@RequestParam(name = "birthday") String birthday, 
			@RequestParam(name = "gender") String gender, 
			@RequestParam(name = "phone") String phone, 
			@RequestParam(name = "address") String address, Model m) {
		
			Accbean.setCustomerName(name);
			Accbean.setAccount(account);
			Accbean.setPassword(password);
			Accbean.setEmail(email);
			Accbean.setBirthday(birthday);
			Accbean.setGender(gender);
			Accbean.setCellphone(phone);
			Accbean.setAddress(address);
			
			LoginBean bean = service.insert(Accbean);
			m.addAttribute("userInfo", bean); 
//			
			return "Elena/CustomerInfoPage";
//		
//			return bean;
	}
	

	
	//@RequestParam = request.getparamater()
	//=request.getParameter("errors").get("userName")
	@RequestMapping(path = "/checklogin.controller", method = RequestMethod.POST)
	public String processCheckLogin(@RequestParam(name = "userName") String user, @RequestParam(name = "userPwd")String pwd, Model m, SessionStatus status) {
		Map<String, String> errors = new HashMap<String, String>();
		m.addAttribute("errors", errors);
		
		if(user == null || user.length() == 0) {
			errors.put("username", "username is required");
		}
		if(pwd == null || pwd.length()==0) {
			errors.put("userpwd", "password is required");
		}
		if(errors!=null && !errors.isEmpty()) {
			return "Elena/login";
		}
//		status.setComplete(); 
		
//		Account account = new Account(user, pwd);
//	    boolean checkUserStatus= accountDao.checkLogin(accout);
//		Account account = new Account(user,pwd);
		int iUserId = service.checkLogin(new LoginBean(user,pwd));
		
		if(iUserId>=0) {
			m.addAttribute("user", user);
			m.addAttribute("pwd", pwd); 
			m.addAttribute("id", iUserId); 

			LoginBean bean = service.select(iUserId);
			m.addAttribute("name", bean.getCustomerName());
			
			return "Elena/CustomerHomePage";
		}
		
		
		errors.put("msg", "please input correct username or password");
		return "Elena/login";
	}
	
	@GetMapping("/backToCustomerHomePage.do")
	public String processBackToCustomerHomePage(Model m) {
		return "Elena/CustomerHomePage";
	}
	
	@GetMapping("/customerInfo.page")
	public String CustomerPage(@RequestParam(name = "id") int uid, Model m) {
		
		LoginBean bean = service.select(uid);
		fill(m, bean);
		
		return "Elena/CustomerInfoPage";

}
	
//	@GetMapping("/customerInfo.page")
//	public String CustomerPage(@RequestParam(name = "id") int uid, Model m, SessionStatus status) {
//		
//		LoginBean bean = account.select(uid);
//		fill(m, bean);
//	}
	
	private void fill(Model m, LoginBean bean) {
		
		m.addAttribute("id", bean.getCustomerID());
		m.addAttribute("name", bean.getCustomerName()); 
		m.addAttribute("account", bean.getAccount()); 
		m.addAttribute("password", bean.getPassword()); 
		m.addAttribute("email", bean.getEmail()); 
		m.addAttribute("birthday", bean.getBirthday()); 
		m.addAttribute("gender", bean.getGender()); 
		m.addAttribute("cellphone", bean.getCellphone()); 
		m.addAttribute("address", bean.getAddress()); 
	}
	
	
	
	@GetMapping("/logout")
	public String logoutandgotohomepage(@Autowired HttpSession session, SessionStatus s) {

		s.setComplete();
		session.invalidate();

		return "redirect:/";
	}
	
}
