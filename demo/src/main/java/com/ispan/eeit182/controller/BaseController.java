package com.ispan.eeit182.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//控制器
@Controller
public class BaseController {
	
	@GetMapping("/")
    public String index() {
    	return "index";
    }
    @GetMapping("/hello")
    public String hello(Model model) {
    	model.addAttribute("helloMessage", "Hello, Spring Boot, 大家好");
    	return "greeting";
    }

}
