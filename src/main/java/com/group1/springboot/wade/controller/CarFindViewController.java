package com.group1.springboot.wade.controller;

import java.io.File;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.wade.Utils.SystemUtils;
import com.group1.springboot.wade.model.Car;
import com.group1.springboot.wade.service.CarServiceImpl;
import com.group1.springboot.wade.validate.CarValidator;


@Controller
public class CarFindViewController {

	@Autowired
	CarServiceImpl carService;
	
	@Autowired
	CarValidator carValidator;
	
	@Autowired
	ServletContext context;
	
	
//	@Autowired
//	private CarServiceImpl service;
//	
//	@RequestMapping("/delete/{id}")
//	public String deleteCar(@PathVariable(name = "id") int id) {
//		service.delete(id);
//		return "redirect:/";		
//	}
	
	//insert-readEvent.jsp
	@GetMapping("/wade")
	public String sendReadCarToInsert(Model model) {
		model.addAttribute("car",new Car());
		return "Wade/readCar";
		
	}
	
	
	
	@ModelAttribute("car")
	public Car getCar(@RequestParam(value="Id", required = false )  Long id) {
		Car car = null;
		if (id != null) {
			car = carService.findById(id);
		} else {
			car = new Car();
		}
		System.out.println("In @ModelAttribute, car=" + car);
		return car;
		
	}
	 
	@PostMapping("/modifyCar/{Id}")
	public String updateCar( @ModelAttribute("car") Car car ,
			BindingResult result , 
			RedirectAttributes ra) {
	
		carValidator.validate(car, result);
	if(result.hasErrors()) {
		
		List<ObjectError> list = result.getAllErrors();
		for(ObjectError error:list) {
			System.out.println("有錯誤"+error);
		}   
		return "redirect:/editCar";	
	}
	Blob blob = null;
	String mimeType = "";
	String name = "";
	MultipartFile carImage = car.getCarImage();
	if (carImage != null && carImage.getSize() > 0) {
		try {
			InputStream is = carImage.getInputStream();
			name = carImage.getOriginalFilename();
			blob = SystemUtils.inputStreamToBlob(is);
			mimeType = context.getMimeType(name);
			car.setPicture(blob);
			car.setMimeType(mimeType);
			
			String ext = SystemUtils.getExtFilename(name);
			// 將上傳的檔案移到指定的資料夾, 目前註解此功能
			try {
				File imageFolder = new File(SystemUtils.CAR_IMAGE_FOLDER);
				if (!imageFolder.exists())
					imageFolder.mkdirs();
				File file = new File(imageFolder, "CarImage_" + car.getId() + ext);
				carImage.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	carService.update(car);
	ra.addFlashAttribute("successMessage", car.getName() + "修改成功");
	// 新增或修改成功，要用response.sendRedirect(newURL) 通知瀏覽器對newURL發出請求
	return "redirect:/queryCar";  
	
	
}

	// 跳轉修改page
	@GetMapping("/modifyCar/{id}")
	public String sendEditPage( 
			@PathVariable Long id,Model model
			) {
		Car car = carService.findById(id);
		model.addAttribute("car",car);
		return "Wade/editCar";
		
	}
	
	
	
	
	


}
