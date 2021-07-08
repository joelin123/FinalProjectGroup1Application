package com.group1.springboot.milton.controller.place;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group1.springboot.milton.model.Place;
import com.group1.springboot.milton.model.ProductInfo;
import com.group1.springboot.milton.service.PlaceServiceImpl;
import com.group1.springboot.utils.SystemUtils;

@Controller
public class PlaceController {

	@Value("\\data\\place.csv")
	String filename;
	
	@Autowired
	PlaceServiceImpl placeService;

	@Autowired
	ServletContext context;
	
	@GetMapping(value="/findAll", produces = "application/json; charset=UTF-8")	
	public @ResponseBody Map<String, Object> findAll(){
		return placeService.findAll(); 
	}
	
	@GetMapping(value="/queryByName", produces = "application/json; charset=UTF-8")	
	public @ResponseBody Map<String, Object> queryByName(
			@RequestParam("rname") String rname
	){
		//System.out.println("rname=" + rname);
		return placeService.queryByName(rname); 
	}
	
	
	@GetMapping(value="/findById/{id}", produces = "application/json; charset=UTF-8")	
	public @ResponseBody ProductInfo findById(
			@PathVariable Long id
			
			){
		return placeService.findById(id); 
	}
	
	//    /findByTypeId?typeId=5&code=301.0&amt=100
	@GetMapping(value="/findByTypeId", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> findByTypeId(
		   @RequestParam Integer typeId,
		   @RequestParam Integer code,
		   @RequestParam Integer amt
			){
		return placeService.findByType(typeId);
	}
	
	
	@GetMapping("/queryRestaurant")
	public String sendQueryRestaurant() {
		return "restaurant/queryRestaurant";
	}
	
	@GetMapping("/readfile/excel/adc")
	public String readFile(Model model) {
		String result = "";
		try {
			ClassPathResource  cpr = new ClassPathResource(filename);
			InputStream is = cpr.getInputStream();
			InputStreamReader  isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			int count = 0;
			while ((line = br.readLine())!= null) {
				System.out.println(line);
				String[] sa = line.split(",");
				
				Integer typeId = Integer.parseInt(sa[0]);
				Double longitude = Double.parseDouble(sa[4].trim());
				Double latitude = Double.parseDouble(sa[5].trim());
				Blob blob = SystemUtils.pathToBlob(sa[7]);
				Clob clob = SystemUtils.pathToClob(sa[8]);
				String mimeType = context.getMimeType(sa[7]);   /// /static/images/place02.jpg
				
				Place place = new Place(typeId, sa[1], sa[2], sa[3], 
                 						longitude, latitude, sa[6], blob, clob, mimeType);
	
				placeService.save(place);
				count++;
			}
			result = "新增" + count + "筆Place記錄";
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		model.addAttribute("result", result);
		
		return "ex02/showResult";
	}	
	
}
