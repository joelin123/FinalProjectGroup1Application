package com.group1.springboot.milton.controller.type;

import java.sql.Clob;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.milton.model.RestaurantType;
import com.group1.springboot.milton.service.TypeServiceImpl;
import com.group1.springboot.utils.SystemUtils;
import com.group1.springboot.validate.PlaceValidator;

@Controller
public class TypeFindViewController {

	@Autowired
	PlaceValidator placeValidator;
	
	@Autowired
	TypeServiceImpl typeService;
	
	@Autowired
	ServletContext context;

	@GetMapping("/insertTypeSuccess")
	public String insertSuccess() {
		return "type/insertSuccess";
	}
	
	@GetMapping("/modifyType/{typeId}")
    public String sendEditPage(
    		@PathVariable Integer typeId, Model model
    ) {
		RestaurantType type = typeService.findById(typeId);
		model.addAttribute("type", type);
		return "type/editType";
	}	
	
	@PostMapping("/modifyRestaurantType/{placeId}")
	public String updateRestaurant(@ModelAttribute("type") RestaurantType type,
			BindingResult result, 
			RedirectAttributes ra) {
		
//		placeValidator.validate(place, result);
		if (result.hasErrors()) {
//          下列敘述可以理解Spring MVC如何處理錯誤			
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println("有錯誤：" + error);
			}
			return "type/editType";
		}
		typeService.update(type);
		ra.addFlashAttribute("successMessage", type.getTypeName() + "修改成功");
		// 新增或修改成功，要用response.sendRedirect(newURL) 通知瀏覽器對newURL發出請求
		return "redirect:/findAll";  
		
		
	}
	@GetMapping("/insertType")
	public String sendInsertRestaurantType(Model model) {
		return "type/type";
	}
	
	@PostMapping("/saveType")
	public @ResponseBody  Map<String, Object> saveRestaurant(
			@RequestParam("typename") String typename, 
			@RequestParam("comment") String comment, 
			@RequestParam("createDate") String createDate 
			
//			BindingResult result
			) {
		 Map<String, Object> map = new HashMap<>();
//		placeValidator.validate(place, result);
//		if (result.hasErrors()) {
////          下列敘述可以理解Spring MVC如何處理錯誤			
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError error : list) {
//				System.out.println("有錯誤：" + error);
//			}
//			return map;
//		}
		Clob clob = SystemUtils.StringToClob(comment);
		Date date = Date.valueOf(createDate);
		RestaurantType type = new RestaurantType(null, typename, clob, date);
		try {
			typeService.save(type);
			map.put("success", "新增成功");   // success fail
		} catch(Exception ex) {
			map.put("fail", ex.getMessage());
		}
		return map;  
	}
	
	@ModelAttribute("type")
	public RestaurantType getPlace1(@RequestParam(value="typeId", required = false ) Integer typeId) {
		System.out.println("------------------------------------------");
		RestaurantType type = null;
		if (typeId != null) {
			type = typeService.findById(typeId);
		} else {
			type = new RestaurantType();
		}
		System.out.println("In @ModelAttribute, type=" + type);
		return type;
	}
	

}
