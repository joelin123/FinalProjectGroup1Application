package com.group1.springboot.elena.login.controller;


import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.elena.login.model.CustomerBean;
import com.group1.springboot.elena.login.service.AdminService;
import com.group1.springboot.elena.login.service.LoginService;
import com.group1.springboot.utils.SystemUtils;



@Controller
@SessionAttributes("CustomerInfo")
public class AdminController {

	@Autowired
	ServletContext context;

	@Autowired
	public CustomerBean customerBean;

	@Autowired
	public AdminService adminService;

	@Autowired
	public LoginService loginService;

	@GetMapping("/exciseLogin")
	public String gotoexciseLoginPage() {
		return "Elena/loginSystem";
	}

	@GetMapping("/exciseSelectAll")
	public String gotoexciseSelectAll() {
		return "Elena/selectall";
	}

	@PostMapping("/exciseLogin")
	public String exciseLoginPage(@RequestParam(name = "userName") String user,
			@RequestParam(name = "userPwd") String pwd, Model m, SessionStatus status) {

//			Map<String, String> errors = new HashMap<String, String>();
//
//			m.addAttribute("errors", errors);
//
//			if (user == null || user.length() == 0) {
//				errors.put("username", "username is required");
//
//			}
//
//			if (pwd == null || pwd.length() == 0) {
//				errors.put("userpwd", "password is required");
//			}
//
//			if (errors != null && !errors.isEmpty()) {
//				return "loginSystem";
//			}

		if (user == "manager123" && pwd == "123") {
			return "redirect:/exciseSelectAll";
		}

		return "redirect:/exciseSelectAll";
	}

	// 查詢全部
	@GetMapping("/exciseSelectAll.controller")
	public @ResponseBody List<CustomerBean> findAllMembers() {
		return adminService.selectUserAll();
	}

	// 查詢單筆AJAX
	@GetMapping(path = "/exciseSelect.controller/{id}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public CustomerBean read(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);

		return adminService.selectUser(id);
	}

	// 新增 => (1)送出空白表單 (2)確認新增
	@GetMapping("/exciseInsert")
	public String main(@ModelAttribute("customer") CustomerBean customer) {
		return "Elena/insert";
	}

	@PostMapping("/exciseInsert.co")
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
		adminService.addUsers(customer);
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
		// 路徑要換 要寫的是mapping的路徑 不是jsp的路徑
		return "redirect:/exciseSelectAll";
	}

//	@RequestMapping(path = "/exciseInsert.controller", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//	@ResponseBody
//	public Map<String, String> actionAdduser(@RequestBody CustomerBean customerBean) {
//		Map<String, String> msg = new HashMap<String, String>();
//		adminService.addUsers(customerBean);
//		msg.put("msg", "新增成功");
//		return msg;
//	}

	// 刪除 => (1)送出空白表單 (2)確認刪除
	@GetMapping(path = "/exciseNotRestDelete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		CustomerBean bean = adminService.selectUser(id);
//		model.addAttribute("id", id);
		model.addAttribute("CustomerInfo", bean);

		return "Elena/deleterest";
	}

	@PostMapping(path = "/customerBean/{id}")
	public @ResponseBody Map<String, String> deleteMember(@PathVariable(required = true, value = "id") Integer id) {
		Map<String, String> map = new HashMap<>();
		try {
			adminService.deleteUser(id);
			map.put("msg", "刪除成功");
			map.put("success", "true");
//			{"msg":"刪除成功", "sucess":"true"}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "刪除失敗");
			map.put("success", "false");
			System.out.println("刪除失敗");
		}
		return map;
	}

	// 修改

	@GetMapping(path = "/exciseNotRestUpdate/{id}")
	public String updateSearch(@PathVariable("id") Integer id, Model model) {
		CustomerBean bean = adminService.selectUser(id);
		// model.addAttribute("id", id);
		model.addAttribute("CustomerInfo", bean);

		return "Elena/update";
	}

	@PostMapping(path = "/exciseNotRestUpdate/{id}")
	public String updateReal(@ModelAttribute("CustomerInfo") CustomerBean CustomerInfo, RedirectAttributes ra) {

		Blob blob = null;
		String mimeType = "";
		String name = "";

		MultipartFile CustomerImage = CustomerInfo.getCustomerImage();

		try {
			InputStream is = CustomerImage.getInputStream();
			name = CustomerImage.getOriginalFilename();
			blob = SystemUtils.inputStreamToBlob(is);
			mimeType = context.getMimeType(name);
			CustomerInfo.setCustomerPhoto(blob);
			CustomerInfo.setMimeType(mimeType);
		} catch (Exception e) {

			e.printStackTrace();
		}
		CustomerInfo.setCustomerPhoto(blob);
		adminService.updateUser(CustomerInfo);
		String ext = SystemUtils.getExtFilename(name);

		try {
			File imageFolder = new File(SystemUtils.PLACE_IMAGE_FOLDER);
			if (!imageFolder.exists())
				// 這邊一定要加s -> mkdirs
				imageFolder.mkdirs();

			File file = new File(imageFolder, "CustomerImage_" + CustomerInfo.getCustomerID() + ext);
			CustomerImage.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常:" + e.getMessage());
		}

		ra.addFlashAttribute("successMessage", "新增成功");
		// 路徑要換 要寫的是mapping的路徑 不是jsp的路徑
		return "redirect:/exciseSelectAll";
	}

}
