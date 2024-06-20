package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BaseController {
	// 傳回首頁的邏輯名稱
	@GetMapping("/")
	public String index() {
		return "index";    
	}
	
	// 傳回可以讀取所有會員資料之表單的邏輯名稱 
	@GetMapping("/showAllMembers")
	public String showAllMembers() {
		return "showAllMembersRest";    
	}
	// 傳回讓使用者修改會員資料之表單的邏輯名稱	
	@GetMapping("/membersEdit/{id}")
	public String editMember(Model model, 
		@PathVariable("id") Integer id) {
		model.addAttribute("pk", id);
		return "updateMemberRest";    
	}
}
