package com.infotran.springboot.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BaseController {
	
	@GetMapping("/")
	public String index() {
		return "index";    //  /WEB-INF/views/index.jsp
	}
	
	
	@GetMapping({"/hello", "/hello/abc/def"})
	public String hello(
			@RequestParam(value="name", defaultValue = "訪客") String visitor, 
			@RequestParam(value="qty", required = false) Integer qty, 
			Model model
			,HttpServletRequest request
			) throws ServletException, IOException {
       
		String message = visitor != null ? visitor + "，您好" : "訪客，您好";

		model.addAttribute("helloMessage", message);
		model.addAttribute("qty", qty);
		
		request.setAttribute("helloMessage2", message);
		
		return "greeting";

	}

}
