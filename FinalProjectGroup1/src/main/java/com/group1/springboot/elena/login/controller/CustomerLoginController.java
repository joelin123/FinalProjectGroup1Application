package com.group1.springboot.elena.login.controller;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.elena.login.model.CustomerBean;
import com.group1.springboot.elena.login.service.LoginService;
import com.group1.springboot.utils.SystemUtils;

@Controller
@RequestMapping("/elena")
@SessionAttributes(names = "CurrentUser")
public class CustomerLoginController {
//使用者登入後的controller
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	ServletContext context;
	
	//去管理者主頁面
	@GetMapping("/gotoManagerHomePage")
	public String directToManagerHomePage() {
		return "Elena/ManagerHomePage";
	}
	
	//使用者登入後點選"我的帳號"
	@GetMapping("/gotoCustomerHomePage")
	public String directToCustomerHomePage() {
		return "Elena/CustomerHomePage";
	}
	
	@GetMapping("/gotoCustomerInfo")
	public String directToCustomerInfoPage() {
		return "Elena/CustomerInfoPage";
	}
	//到update的頁面
	@GetMapping("/updateuserinfo")
	public String directToEditCustomerInfoPage() {
		return "Elena/CustomerInfoUpdate";
	}
	
	//執行update
	@PostMapping("/updateuserinfo")
	public String updateRestaurant(@ModelAttribute("CurrentUser") CustomerBean cusBean,
			BindingResult result, 
			RedirectAttributes ra) {
		
		
		Blob blob = null;
		String mimeType = "";
		String name = "";
		MultipartFile placeImage = cusBean.getCustomerImage();
		if (placeImage != null && placeImage.getSize() > 0) {
			try {
				InputStream is = placeImage.getInputStream();
				name = placeImage.getOriginalFilename();
				blob = SystemUtils.inputStreamToBlob(is);
				mimeType = context.getMimeType(name);
				cusBean.setCustomerPhoto(blob);
				cusBean.setMimeType(mimeType);
				
				String ext = SystemUtils.getExtFilename(name);
				// 將上傳的檔案移到指定的資料夾, 目前註解此功能
				try {
					File imageFolder = new File(SystemUtils.PLACE_IMAGE_FOLDER);
					if (!imageFolder.exists())
						imageFolder.mkdirs();
					File file = new File(imageFolder, "MemberImage_" + cusBean.getCustomerID() + ext);
					placeImage.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		}
		Integer id = cusBean.getCustomerID();
		cusBean.setCustomerID(id);
		loginService.updateCustomer(cusBean);
		
		
		
		ra.addFlashAttribute("updateSuccess", "會員資料修改成功");
		// 新增或修改成功，要用response.sendRedirect(newURL) 通知瀏覽器對newURL發出請求
		return "redirect:/elena/updateuserinfo";  
				
	}
	
	
	
	
	
//	
//	@ModelAttribute("CusUpdate")
//	public CustomerBean getPlace1(@RequestParam(value="customerID", required=false) Integer customerID) {	
//		System.out.println("---------------------------");
//		CustomerBean bean = null;
//		if(customerID != null) {
//			bean = loginService.getCustomerById(customerID);
//		}else {
//			bean = new CustomerBean();
//		}
//		System.out.println("In @ModelAttribute" + bean);
////		model.addAttribute("place",p);	//這行可寫可不寫，他會自己生
//
//		return bean;
//
//	}
//	
	
	
	
	
}
