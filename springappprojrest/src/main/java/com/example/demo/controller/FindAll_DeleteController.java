package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;

@Controller
public class FindAll_DeleteController {

	final static Logger log = LoggerFactory.getLogger(FindAll_DeleteController.class);
	
	MemberService memberService;

//	@Autowired
	public FindAll_DeleteController(MemberService memberService) {
		this.memberService = memberService;
		
	}

	// 接收前端的Ajax請求，由memberService元件讀取所有會員資料，轉換為JSON格式後傳回前端
	@GetMapping("/members")
	public @ResponseBody List<Member> findAll(Model model) {
		return memberService.findAll();
	}
	// 依照主鍵值刪除會員紀錄
	@DeleteMapping("/members/{id}")
	public @ResponseBody Map<String, String> deleteById(
			@PathVariable(value="id", required = false ) Integer id) {
		
		Map<String, String> map = new HashMap<>();
		try { 
			memberService.deleteById(id);
			map.put("success", "刪除成功");
		} catch(Exception e) {
			map.put("fail", "刪除失敗: " + e.getMessage());
		}
		return map;
	}
	
	// 當新增會員資料時, 檢查帳號是否可用
	@PostMapping(value = "/checkMemberId", produces = { "application/json" })
	public @ResponseBody Map<String, String> checkMemberId(
			@RequestParam String memberId) {
		Map<String, String> map = new HashMap<>();
		String id = memberService.checkMemberId(memberId);
		System.out.println("memberId=" + memberId + ", id=" + id);
		map.put("memberId", id);
		return map;
	}
}
