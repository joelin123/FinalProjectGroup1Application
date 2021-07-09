package com.group1.springboot.wade.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.wade.Utils.SystemUtils;
import com.group1.springboot.wade.model.Car;
import com.group1.springboot.wade.service.CarServiceImpl;
import com.group1.springboot.wade.validate.CarValidator;





@Controller
public class CarController {
	
	@Value("\\data\\Wade\\CarT1.csv")
	String filename;
	
	@Autowired
	CarServiceImpl carService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	CarValidator carValidator;
	
	
	
	@GetMapping(value="/findAll",produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Car> findAll(){
		return carService.findAll();		
	}
	
    
	@GetMapping(value="/findById/{id}", produces = "application/json; charset=UTF-8")
	public @ResponseBody Car findById(@PathVariable Long id) {
		return carService.findById(id);	
	}

	
	
	@GetMapping(value="/queryByName", produces = "application/json; charset=UTF-8")
	public@ResponseBody List<Car> queryByName(
			@RequestParam String name
			){
		return carService.queryByName(name);
		
	}
	
//	@RequestMapping(value="/deletecar/{id}",method = RequestMethod.GET)
//	public String delete(@PathVariable Long id) {
//		return "redirect:/queryCar";
//	}
	


		// insert後的跳轉 SAVE
		@PostMapping("/wade")
		public String saveReadCarToInsert(Car car,RedirectAttributes ra,
				BindingResult result
				) {
			
			carValidator.validate(car, result);
			if(result.hasErrors()) {
			
				return "Wade/readCar";
			}
			
			
			Blob blob = null;
			String mimeType  = "";
			String name="";
			
			MultipartFile carImage = car.getCarImage();
			
			try {
				InputStream is = carImage.getInputStream();
			    name = carImage.getOriginalFilename();
				blob = SystemUtils.inputStreamToBlob(is);
				mimeType = context.getMimeType(name);
				car.setPicture(blob);
				car.setMimeType(mimeType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			carService.save(car);
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
			ra.addFlashAttribute("successMessage",car.getName()+ "新增成功!" );
			//新增或修改成功,要用response.sendRedirect(newURL) 通知瀏覽器對newURL發出請求
			return "redirect:/queryCar";	
		} 
		
	
	@GetMapping("/insertSuccess")
	public String insertSuccess() {
		return "Wade/insertSuccess";
		
	}
	
	
/*	
	@GetMapping(value="/shoeEvent", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> findAll(){
		return eventService.findAll(); 
	}
*/		
	@GetMapping(value="/findByName", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Car> findByName(
			@RequestParam(value="rname",required=false) String rname
			){
		System.out.println("rname" + rname);
		return carService.findByName(rname);
	}

	
	
	


		//query-queryEvent.jsp
		@GetMapping("/queryCar")
		public String sendQueryCar() {
			return "Wade/queryCar";
			
		}
	
	
	
	//讀取資料
	@GetMapping("/readCar")
	public String readFile(Model model) {
		 String result = "";
		try {
			ClassPathResource cpr = new ClassPathResource(filename);
			InputStream is = cpr.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line = "" ;
			int count = 0;
			while((line = br.readLine())!=null) {
				//System.out.println(line) 在console印出
				//======以下是將資料放進資料庫
				String[] sa = line.split(",");
				Long id = Long.parseLong(sa[0]);
				Integer price = Integer.parseInt(sa[2]);
				Integer seat = Integer.parseInt(sa[3]);
				Double suitcase = Double.parseDouble(sa[4].trim());
				Double handbag = Double.parseDouble(sa[5].trim());
				
			//	Clob clob = SystemUtils.pathToClob(sa[6]);
				Blob blob = SystemUtils.pathToBlob(sa[8]);	
				String mimeType = context.getMimeType(sa[8]);
				
				Car car = new Car(id,sa[1],price,seat,suitcase,handbag,sa[6],sa[7],blob,mimeType);
				
				carService.save(car);
				count++;
		
			}
			result = "讀取:有"+ count +"筆";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = e.getMessage();
		}
		model.addAttribute("result", result);
		return "readCar" ;
	}
	
	
	
}


