package com.element.cloud.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.element.cloud.bean.FileBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class ListFiles {

	static String path = "D:\\home\\site\\Cloud_Element\\";

	@RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getall(@RequestParam("dir") String dir1) {

		List fileList = new ArrayList<FileBean>();
		String dirPath =path+(!dir1.isEmpty()?"/":"")+dir1;
		File dir = new File(dirPath);
		try {
			if(!dir.exists())
				dir.mkdir();
		}catch (Exception e) {
			return "[Directory not present]";
		}
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				FileBean fb = new FileBean();
				fb.setFileName(file.getName());
				fb.setFileSize(file.length());
				fb.setFile(file.isFile());
				fileList.add(fb);
			}
		} else {
			return "[No Files present in this dir]";
		}
		Gson gson = new Gson();
		Type type = new TypeToken<List<FileBean>>() {
		}.getType();
		String json = gson.toJson(fileList, type);
		return json;
	}

	@RequestMapping(value = "/download/{filename:.+}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("filename") String fileName, HttpServletResponse res) {
		try {
			File file = new File(path + "/" + fileName);
			if (!file.exists()) {
				String errorMessage = "[File Not exist]";
				OutputStream outputStream = res.getOutputStream();
				outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
				return;
			}
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			res.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, res.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file) {
			try {
				byte[] bytes = file.getBytes();
				File serverFile = new File(path + "/" + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				return "[Successfully uploaded " + file.getOriginalFilename()+"]";
			} catch (Exception e) {
				return "[Upload Failed" + e.getMessage()+"]";
			}
	}
}
