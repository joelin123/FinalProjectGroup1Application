package com.group1.springboot.elena.login.controller;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.elena.login.model.CustomerBean;
import com.group1.springboot.elena.login.service.JavaMailJService;
import com.group1.springboot.elena.login.service.LoginService;
import com.group1.springboot.utils.SystemUtils;

@Controller
@RequestMapping("/elena")
@SessionAttributes(names = ("CurrentUser"))	
public class MainPageController {
//首頁的controller
	
	@Autowired
	ServletContext context;

	@Autowired
	LoginService loginService;
	
	@Autowired
	JavaMailJService mailService;
	
	

	@GetMapping("/gotohomepagewhenloggedin")
	public String directToUserMainPage() {
		return "/";
	}

	// 到註冊頁面
	@GetMapping("/registration")
	public String goToRegistrationPage(@ModelAttribute("customer") CustomerBean customer) {

		return "Elena/RegisterForm";
	}
	
	@GetMapping("/registerOut")
	public String redirectToOutPage() {
		return "Elena/RegisterOut";
	}

	// 執行註冊(包含圖片)
	@PostMapping("/registration")
	public String processRegistration(CustomerBean customer, RedirectAttributes ra) {
		Blob blob = null;
		String mimeType = "";
		String name = "";

		MultipartFile CustomerImage = customer.getCustomerImage();

		try {
			InputStream is = CustomerImage.getInputStream();
			name = CustomerImage.getOriginalFilename();
			blob = SystemUtils.inputStreamToBlob(is);
			mimeType = context.getMimeType(name);
			customer.setCustomerPhoto(blob);
			customer.setMimeType(mimeType); 
		} catch (Exception e) {

			e.printStackTrace();
		}
		customer.setCustomerPhoto(blob);
		loginService.insertCustomer(customer);
		String ext = SystemUtils.getExtFilename(name);

		try {
			File imageFolder = new File(SystemUtils.PLACE_IMAGE_FOLDER);
			if (!imageFolder.exists())
				// 這邊一定要加s -> mkdirs
				imageFolder.mkdirs();

			File file = new File(imageFolder, "CustomerImage_" + customer.getCustomerID() + ext);
			CustomerImage.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
		}

		ra.addFlashAttribute("successMessage", "新增成功");
		// 路徑要換
		return "redirect:/elena/registerOut";
	}

	// 進入登入頁面
	@GetMapping("/login")
	public String checkLogin() {

		return "Elena/login";
	}

	@PostMapping("/login")
	public String processCheckLogin(@RequestParam(name = "userName") String user,
			@RequestParam(name = "userPwd") String pwd, Model m, RedirectAttributes ra, SessionStatus status) {
	
		 if(user.equals("Admin123") && pwd.equals("Admin123")) {
			
			return "redirect:/exciseSelectAll";
		}
		CustomerBean bean = loginService.checkLogin(user, pwd);
		
		if (bean != null) {
			int id = bean.getCustomerID();
			String name = bean.getCustomerName();
			
			m.addAttribute("CurrentUser", bean);
			
			ra.addFlashAttribute("id", id);
			ra.addFlashAttribute("name", name);
			ra.addFlashAttribute("Users", bean);
			ra.addFlashAttribute("loginSuccess", "登入成功!");
			return "redirect:/";
		}
	
		ra.addFlashAttribute("Failed", "帳號密碼有誤");
		return "redirect:/elena/login";
	}
	
	//忘記密碼
	@GetMapping("/forgetpassword")
	public String directToForgetPasswordPage() {
		return "Elena/ForgetPasswordPage";
	}
	
	@PostMapping("/forgetpassword")
	public String forgetPasswordAction(@RequestParam("email") String email) {
		CustomerBean bean = (CustomerBean) loginService.getCustomerByEmail(email);

		if (bean != null) {
			String existmail = bean.getEmail();
			mailService.sendMailForget(existmail);
		}

		return "Elena/ForgetPasswordOut";
	}
	
	
	
	
	
	@GetMapping("/logout")
	public String logoutandgotohomepage(SessionStatus s) {
		s.setComplete();

		return "redirect:/";
	}


}
