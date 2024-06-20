package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.example.demo.validator.MemberValidator;

@Controller
public class MemberUpdateController extends AbstractController {

	final static Logger log = LoggerFactory.getLogger(MemberUpdateController.class);

	MemberService memberService;
	
//	@Autowired
	public MemberUpdateController(MemberService memberService) {
		this.memberService = memberService;
	}
	// 接收前端送來之請求(/members/{id})，由下方的 findMemberFromDBToBeUpdated()方法，
	// 依鍵值讀取Member資料，放入Model中，傳回可供修改之視圖的邏輯名稱"updateMember"
	@GetMapping("/members/{id}")
	public String findMemberToUpdate(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("id", id);
		return "updateMember";
	}
	// 處理前端以PUT方法送來的會員資料以便進行更新
	@PutMapping("/members/{id}")
	public String processMemberData(
			@ModelAttribute("member") Member member, 
			BindingResult result,
			@PathVariable("id") Integer id, 
			Model model, RedirectAttributes ra) {
		
		log.info("Member in @PutMapping: " + member);
		
		new MemberValidator().validate(member, result);
		
		if (result.hasErrors()) {
			List<FieldError> list  =   result.getFieldErrors();
			for(FieldError error : list) {
				System.out.println("error:" + error);
			}
			return "updateMember";
		}
		
		memberService.update(member);
		ra.addFlashAttribute("insertSuccess", "更新成功");
		return "redirect:/members";
	}
	// 當本類別的findMemberToUpdate()與processMemberData()兩方法執行之前，
	// 本方法會依照傳來的鍵值讀取一筆Member紀錄以供上述兩方法使用
	@ModelAttribute
	public Member findMemberFromDBToBeUpdated(
		@PathVariable(value="id", required = false ) Integer id ) {
		Member member = null;
		member = memberService.findById(id);
		// 必須先移除，否則更新前檢查是否鍵值重複時JPA會先Update才進行查詢
		memberService.detach(member) ;
		// member = new Member();  錯誤寫法
		log.info("1. Member in @ModelAttribute#findMemberFromDBToBeUpdated(): " + member);
		return member;
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder, WebRequest request) {
//		// java.util.Date
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		dateFormat.setLenient(false);
//		CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
//		binder.registerCustomEditor(java.util.Date.class, ce);
//		// java.sql.Date
//		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat2.setLenient(false);
//		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true);
//		binder.registerCustomEditor(java.sql.Date.class, ce2);
//	}
}
