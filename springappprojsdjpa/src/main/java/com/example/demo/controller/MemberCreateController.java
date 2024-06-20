package com.example.demo.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Member;
import com.example.demo.service.MemberService;
import com.example.demo.validator.MemberValidator;

@Controller
public class MemberCreateController {

	final static Logger log = LoggerFactory.getLogger(MemberCreateController.class);
	// 新增一筆Member紀錄所使用的URI
	final static String CREARE_URI = "/insertMemberForm";
	// 為了方便測試功能而定義的List物件
	static List<Member> members = null;

	MemberService memberService;

//	@Autowired
	public MemberCreateController(MemberService memberService) {
		this.memberService = memberService;
		if (members == null) {
			// 為了加快資料的輸入，建立六筆資料以便節省輸入資料的時間
			members = createInitialMembers();
			log.info("The size of members: " + members.size());
		}
	}
	// 傳回可以輸入會員資料之空白表單的邏輯名稱
	@GetMapping(CREARE_URI)
	public String getForm() {
		return "registerMember";
	}

	// 接收前端送來之表單格式(application/x-www-form-urlencoded)的會員資料，
	// 經過檢查後若無錯誤呼叫memberService寫入表格內。
	// 若含有錯誤呼叫原輸入資料的JSP送回含有輸入資料與錯誤訊息的畫面。
	// @ModelAttribute: 一定要加，否則當資料驗證失敗時，導向員輸入畫面時會找不到Backing Form Bean而當掉
	@PostMapping(CREARE_URI)
	public String processMemberData(@ModelAttribute Member member, BindingResult result, Model model,
			RedirectAttributes ra) {

		log.info("Member in @PostMapping: " + member);
		// 檢查表單資料
		new MemberValidator().validate(member, result);

		if (memberService.findByMemberId(member.getMemberId()) != null) {
			result.rejectValue("memberId", "member.memberId.duplicated", "帳號已經存在，請重新輸入");
		}

		if (result.hasErrors()) {
			// 有錯誤，送回原輸入資料的表單，內含原輸入資料與相關的錯誤訊息
			List<FieldError> list = result.getFieldErrors();
			for (FieldError error : list) {
				log.info("error:" + error);
			}
			return "registerMember";
		}

		member.setExtra("測試用");
		member.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		memberService.save(member);
		// 新增或修改成功必須使用RedirectAttributes的addFlashAttribute()方法通知前端成功訊息。
		// RedirectAttributes的addFlashAttribute()方法需要搭配"redirect:"
		ra.addFlashAttribute("insertSuccess", "新增成功");
		return "redirect:" + CREARE_URI;
	}

	// 註釋@ModelAttribute("可在此指定屬性物件的識別字串")的功能為將方法之傳回值存入model內，
	// 其功能等於model.addAttribute("member", member);
	@ModelAttribute
	public Member preSendEmptyForm(@RequestParam(value = "memberId", required = false) String memberId, Model model) {
		Member member = null;

		if (memberId == null) {
			if (members.size() > 0) {
				int index = (int) (Math.random() * members.size());
				member = members.remove(index);
			} else {
				member = new Member();
			}
		} else {
			member = new Member();
		}
		log.info("Member in @ModelAttribute#preSendEmptyForm(): " + member + ", memberId=" + memberId);
		return member;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(java.util.Date.class, ce);
		// java.sql.Date
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true);
		binder.registerCustomEditor(java.sql.Date.class, ce2);
	}
	// 為了方便測試新增功能而寫的輔助方法：建立六筆測試資料
	private List<Member> createInitialMembers() {
		Member m0 = new Member(null, "sg001", "吳一男(sdjpa)", 123.4, Date.valueOf("1960-5-8"));
		Member m1 = new Member(null, "sg067", "姜曉(sdjpa)", 2500.4, Date.valueOf("1985-12-17"));
		Member m2 = new Member(null, "sg240", "智英(sdjpa)", 585.0, Date.valueOf("1992-4-1"));
		Member m3 = new Member(null, "sg218", "曹尚佑(sdjpa)", 123.4, Date.valueOf("1984-10-15"));
		Member m4 = new Member(null, "sg456", "成奇勳(sdjpa)", 9500.0, Date.valueOf("1982-8-9"));
		Member m5 = new Member(null, "sg199", "阿里(sdjpa)", 180.0, Date.valueOf("1992-11-3"));
//		List<Member> members0 = Arrays.asList(m0, m1, m2, m3, m4, m5);
		List<Member> members = new ArrayList<>(Arrays.asList(m0, m1, m2, m3, m4, m5));
		return members;
	}
}
