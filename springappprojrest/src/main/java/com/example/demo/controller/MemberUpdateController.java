package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.example.demo.validator.MemberValidator;

@Controller
@PropertySource("classpath:messages_zh_TW.properties")
public class MemberUpdateController extends AbstractController {

	final static Logger log = LoggerFactory.getLogger(MemberUpdateController.class);

	MemberService memberService;
	
	Environment env;
	
//	@Autowired
	public MemberUpdateController(MemberService memberService, Environment env) {
		this.memberService = memberService;
		this.env = env;
	}
	// 接收前端送來之Ajax請求，依鍵值讀取Member資料，轉換為JSON格式後送回
	@GetMapping("/members/{id}")
	public @ResponseBody Member findMemberToUpdate(Model model, @PathVariable("id") Integer id) {
		return memberService.findById(id);
	}
	
	// 處理前端以PUT方法送來的會員更新資料
	@PutMapping("/members/{id}")
	public @ResponseBody Map<String, String> processMemberData(
			@RequestBody Member member, 
			BindingResult result,
			@PathVariable("id") Integer id, 
			Model model) {
		
		log.info("Member in @PutMapping: " + member);
		
		Member member0 = null;
		if (id != null) {
			member0 = memberService.findById(id);
			if (member0 == null) {
				throw new RuntimeException("鍵值不存在, id=" + id);
			}
			// memberService.detach(member0);
		} else {
			throw new RuntimeException("鍵值不存在, id=" + id);
		}
		copyUnupdateField(member0, member);
		
		Map<String, String> map = new HashMap<>();
		
		new MemberValidator().validate(member, result);
		
		if (result.hasErrors()) {
			collectErrorMessage(map, result);
		}
		if (map.size() > 0) {
			map.put("success", "");  // 清除原有的成功訊息
			return map;
		}
		memberService.update(member);
		map.put("success", "更新成功");
		return map;
	}
	
	private void copyUnupdateField(Member member0, Member member) {
		member.setExtra(member0.getExtra());
		member.setRegisterTime(member0.getRegisterTime());
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
	
	public void collectErrorMessage(Map<String, String> map, BindingResult result) {
		List<FieldError> list = result.getFieldErrors();
		
		for (FieldError error : list) {
			String errorMessage = env.getProperty(error.getCode());
			log.info("error:" + error.getField() + ", " + error.getCode() + ", msg=" + errorMessage);
			map.put(error.getField(), errorMessage);
		}
		
	}
}
