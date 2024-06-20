package com.infotran.springboot.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.infotran.springboot.model.Member;
import com.infotran.springboot.service.MemberService;
import com.infotran.springboot.validator.MemberValidator;

@Controller
public class MemberCreateController extends AbstractController{

	final static Logger log = LoggerFactory.getLogger(MemberCreateController.class);

	final static String CREARE_URI = "/insertMemberForm";

	static List<Member> members = null;

	MemberService memberService;

//	@Autowired
	public MemberCreateController(MemberService memberService) {
		this.memberService = memberService;
		if (members == null) {
			members = createInitialMembers();
			log.info("The size of members: " + members.size());
		}
	}

	@GetMapping(CREARE_URI)
	public String getForm() {
		return "registerMember";
	}

	// @ModelAttribute: 一定要加，否則當資料驗證失敗時，導向員輸入畫面時會找不到Backing Form Bean而當掉
	@PostMapping(CREARE_URI)
	public String processMemberData(@ModelAttribute Member member, BindingResult result, Model model,
			RedirectAttributes ra) {

		log.info("Member in @PostMapping: " + member);

		new MemberValidator().validate(member, result);

		if (memberService.findByMemberId(member.getMemberId()) != null) {
			result.rejectValue("memberId", "member.memberId.duplicated", "帳號已經存在，請重新輸入");
		}

		if (result.hasErrors()) {
			List<FieldError> list = result.getFieldErrors();
			for (FieldError error : list) {
				log.info("error:" + error);
			}
			return "registerMember";
		}

		member.setExtra("測試用");
		member.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		memberService.save(member);
		ra.addFlashAttribute("insertSuccess", "新增成功");
		return "redirect:" + CREARE_URI;
	}

	// 此註釋的功能為自動將傳回值存入model內， model.addAttribute("member", member);
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

	private List<Member> createInitialMembers() {
		Member m0 = new Member(null, "sg001", "吳一男", 123.4, Date.valueOf("1960-5-8"));
		Member m1 = new Member(null, "sg067", "姜曉", 2500.4, Date.valueOf("1985-12-17"));
		Member m2 = new Member(null, "sg240", "智英", 585.0, Date.valueOf("1992-4-1"));
		Member m3 = new Member(null, "sg218", "曹尚佑", 123.4, Date.valueOf("1984-10-15"));
		Member m4 = new Member(null, "sg456", "成奇勳", 9500.0, Date.valueOf("1982-8-9"));
		Member m5 = new Member(null, "sg199", "阿里", 180.0, Date.valueOf("1992-11-3"));
		List<Member> members = new ArrayList<>(Arrays.asList(m0, m1, m2, m3, m4, m5));
		return members;
	}
}
