package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
	// 傳回首頁的邏輯名稱
	@GetMapping("/")
	public String index() {
		return "index";    
	}

}
