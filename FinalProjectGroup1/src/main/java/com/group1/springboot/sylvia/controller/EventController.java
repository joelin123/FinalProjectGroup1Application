package com.group1.springboot.sylvia.controller;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group1.springboot.sylvia.Utils.SystemUtils;
import com.group1.springboot.sylvia.model.Event;
import com.group1.springboot.sylvia.service.EventServiceImpl;
import com.group1.springboot.sylvia.validate.EventValidator;





@Controller
public class EventController {
	
	@Value("\\data\\Sylvia\\EventV1.csv")
	String filename;
	
	@Autowired
	EventServiceImpl eventService;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	EventValidator eventValidator;
	
	@GetMapping(value="/findAll",produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Event> findAll(){
		return eventService.findAll();		
	}
	
    
	@GetMapping(value="/findById/{id}", produces = "application/json; charset=UTF-8")
	public @ResponseBody Event findById(@PathVariable Long id) {
		return eventService.findById(id);	
	}

	
	
	@GetMapping(value="/queryByName", produces = "application/json; charset=UTF-8")
	public@ResponseBody List<Event> queryByName(
			@RequestParam String name
			){
		return eventService.queryByName(name);
		
	}
	
	
	// insert後的跳轉 SAVE
		@PostMapping("/sylvia")
		public String saveReadEventToInsert(Event event,RedirectAttributes ra,
				BindingResult result
				) {
			
			eventValidator.validate(event, result);
			if(result.hasErrors()) {
			
				return "Sylvia/readEvent";
			}
			
			
			Blob blob = null;
			String mimeType  = "";
			String name="";
			
			MultipartFile eventImage = event.getEventImage();
			
			try {
				InputStream is = eventImage.getInputStream();
			    name = eventImage.getOriginalFilename();
				blob = SystemUtils.inputStreamToBlob(is);
				mimeType = context.getMimeType(name);
				event.setPicture(blob);
				event.setMimeType(mimeType);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			eventService.save(event);
			String ext = SystemUtils.getExtFilename(name);
			// 將上傳的檔案移到指定的資料夾, 目前註解此功能
			try {
				File imageFolder = new File(SystemUtils.EVENT_IMAGE_FOLDER);
				if (!imageFolder.exists())
					imageFolder.mkdirs();
				File file = new File(imageFolder, "EventImage_" + event.getId() + ext);
				eventImage.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
			ra.addFlashAttribute("successMessage",event.getName()+ "新增成功!" );
			//新增或修改成功,要用response.sendRedirect(newURL) 通知瀏覽器對newURL發出請求
			return "redirect:/queryEvent";	
		} 
		
	
	@GetMapping("/insertSuccess")
	public String insertSuccess() {
		return "Sylvia/insertSuccess";
		
	}
	
	
/*	
	@GetMapping(value="/shoeEvent", produces = "application/json; charset=UTF-8")
	public @ResponseBody Map<String, Object> findAll(){
		return eventService.findAll(); 
	}
*/		
	@GetMapping(value="/findByName", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Event> findByName(
			@RequestParam(value="rname",required=false) String rname
			){
		System.out.println("rname" + rname);
		return eventService.findByName(rname);
	}

	
	
	//query-queryEvent.jsp
		@GetMapping("/queryEvent")
		public String sendQueryEvent() {
			return "Sylvia/queryEvent";
			
		}
	
	
	
	//讀取資料
	@GetMapping("/readEvent")
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
				Double longitude = Double.parseDouble(sa[3].trim());
				Double latitude = Double.parseDouble(sa[4].trim());
				
			//	Clob clob = SystemUtils.pathToClob(sa[6]);
				Blob blob = SystemUtils.pathToBlob(sa[7]);	
				String mimeType = context.getMimeType(sa[7]);
				
				Event event = new Event(id,sa[1],price,longitude,latitude,sa[5],sa[6],blob,mimeType);
				
				eventService.save(event);
				count++;
		
			}
			result = "讀取:有"+ count +"筆";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			result = e.getMessage();
		}
		model.addAttribute("result", result);
		return "readEvent" ;
	}
	
	
	
}


