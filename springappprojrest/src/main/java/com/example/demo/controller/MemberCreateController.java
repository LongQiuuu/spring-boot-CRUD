package com.example.demo.controller;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.example.demo.validator.MemberValidator;

@Controller
@PropertySource("classpath:messages_zh_TW.properties")
public class MemberCreateController extends AbstractController {

	final static Logger log = LoggerFactory.getLogger(MemberCreateController.class);

	final static String CREARE_URI = "/insertMemberForm";
	
	// 為了方便測試功能而定義的List物件
	static List<Member> members = null;

	MemberService memberService;

	Environment env;     // 
	
//	@Autowired
	public MemberCreateController(MemberService memberService, Environment env) {
		this.memberService = memberService;
		this.env = env;
	}
	// 傳回可以輸入會員資料之空白表單的邏輯名稱
	@GetMapping(CREARE_URI)
	public String getForm() {
		return "registerFormRest";
	}

	// 接收前端送來之JSON格式的會員資料經過檢查後呼叫memberService寫入表格內
	@PostMapping("/members")
	public @ResponseBody Map<String, String> processMemberData(
			@RequestBody Member member, 
			BindingResult result, Model model) {
		log.info("Member in @PostMapping: " + member);
		// map: 裝載傳回給前端程式之訊息的Map物件
		Map<String, String> map = new HashMap<>();

		new MemberValidator().validate(member, result);

		if (memberService.findByMemberId(member.getMemberId()) != null) {
			map.put("memberIdExist", "帳號已經存在，請重新輸入");
		}

		if (result.hasErrors()) {
			collectErrorMessage(map, result);
		}
		if (map.size() > 0) {
			map.put("success", "");  // 清除原有的成功訊息
			return map;
		}
		member.setExtra("測試用");
		member.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		memberService.save(member);
		map.put("success", "新增成功");
		return map;
	}
	// 由BindingResult中取出錯誤代號，然後經由 Environment 物件以錯誤代號為鍵值讀取訊息檔(.properties)
	// 內的訊息
	public void collectErrorMessage(Map<String, String> map, BindingResult result) {
		List<FieldError> list = result.getFieldErrors();
		
		for (FieldError error : list) {
			String errorMessage = env.getProperty(error.getCode());
			log.info("error:" + error.getField() + ", " + error.getCode() + ", msg=" + errorMessage);
			map.put(error.getField(), errorMessage);
		}
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
