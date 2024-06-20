package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.MemberService;

@Controller
public class FindAll_DeleteController {

	final static Logger log = LoggerFactory.getLogger(FindAll_DeleteController.class);
	
	MemberService memberService;

//	@Autowired
	public FindAll_DeleteController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 當使用者需要: 1.讀取所有紀錄, 2. 刪除某筆紀錄後重新顯示所有紀錄以確保紀錄已刪除
	// 會呼叫此方法
	@RequestMapping(value="/members", method= {RequestMethod.GET, RequestMethod.DELETE})
	public String findAll(Model model) {
		model.addAttribute("memberList", memberService.findAll());
		return "showAllMembers";
	}
	// 依照主鍵值刪除會員紀錄
	@DeleteMapping("/members/{id}")
	public String deleteById(@PathVariable(value="id", required = false ) Integer id) {
		memberService.deleteById(id);
		return "forward:/members";
	}
	
}
