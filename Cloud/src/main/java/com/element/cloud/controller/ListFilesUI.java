package com.element.cloud.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.element.cloud.bean.FileBean;

@Controller
public class ListFilesUI {

	static String path = "D:\\home\\site\\Cloud_Element\\";
	
	@RequestMapping(value = "/listAll/UI")
	public ModelAndView listAll(@RequestParam("dir") String dir1) {
		
		List fileList = new ArrayList<FileBean>();
		String dirPath =path+(!dir1.isEmpty()?"/":"")+dir1;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				FileBean fb = new FileBean();
				fb.setFileName(file.getName());
				fb.setFileSize(file.length());
				fb.setFile(file.isFile());
				fileList.add(fb);
			}
		}
		ModelAndView mv = new ModelAndView("allfiles");
		mv.addObject("Files", fileList);
		mv.addObject("curr_dir",dirPath);
		return mv;
	}

}
