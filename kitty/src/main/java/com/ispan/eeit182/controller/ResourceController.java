package com.ispan.eeit182.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ispan.eeit182.entity.Student;
import com.ispan.eeit182.utlis.SystemService;

@Controller
public class ResourceController {
	
	ServletContext servletContext;
	
	@Autowired
	public ResourceController (ServletContext servletContext) {
		this.servletContext=servletContext;
	}
	
	
	//@GetMapping("裡面放ＪＳＰ的value")
	@GetMapping("/showResourse")
	public String getResource1() {
		
		//return "轉到新網頁";
		return "showPicture";
	}
	
	//讀圖片 
	@GetMapping("/getKitty/{filename}")
	public ResponseEntity<byte[]>  getKitty(@PathVariable String filename) {
		ResponseEntity<byte[]> re = null;
		HttpHeaders headers = new HttpHeaders();
		String path = "/static/images/" + filename ;
		try {
			ClassPathResource  classPathResource = new ClassPathResource(path);
			File file = classPathResource.getFile();
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getCanonicalPath());
			//  C:\\abc\\def\\ijk\\kitty.gif
			Path path1 = file.toPath();
			byte[] media = Files.readAllBytes(path1);
			
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		    String mimeType = servletContext.getMimeType(filename);
		    MediaType mediaType = MediaType.valueOf(mimeType);
		    System.out.println("mediaType =" + mediaType);
		    headers.setContentType(mediaType);
			
			re = new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
			return re;
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return re;
	}
	//讀文字
	@GetMapping("/readTextFile/{filename}/{encoding}")
	public String readText(
			@PathVariable String filename ,
			@PathVariable String encoding ,
			Model model) {
		List<Student> students =new ArrayList<>();
		String path ="/text/"+filename;
		ClassPathResource classPathResource = new ClassPathResource(path);
		try {
			InputStream is =classPathResource.getInputStream();
			BufferedReader br =new BufferedReader (new InputStreamReader(is,encoding));
			String line ="";
			while ((line =br.readLine())!=null) {
				String[] sa=line.split(",");
				Student student  =new Student (sa[0],sa[1],Date.valueOf(sa[2]),sa[3]);
				students.add(student);
			}
			//把資料交給網頁
			model.addAttribute(students);
			System.out.println(students);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "showStudents";
	}
}
