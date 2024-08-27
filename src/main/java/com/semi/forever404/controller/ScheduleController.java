package com.semi.forever404.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.semi.forever404.model.vo.BigSchedule;
import com.semi.forever404.model.vo.Photo;
import com.semi.forever404.service.GroupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleController {
	
	private String path = "\\\\192.168.10.28\\forever404\\storage\\";
	@Autowired
	private GroupService service;
	
	@PostMapping("/testupload")
	public String testupload(List<MultipartFile> files) throws IllegalStateException, IOException {
		Photo photo;
		for(MultipartFile f : files) {
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString() + "_" + f.getOriginalFilename();
			File file = new File(path + fileName);
			f.transferTo(file);
			
			
			String url = path + fileName;
			BigSchedule bg = new BigSchedule();
			bg.setBsCode(1);
			photo = new Photo(url, bg);
			service.imgLoad(photo);
		}
		return "redirect:/";
	}
}
