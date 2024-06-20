package com.infotran.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infotran.springboot.service.MemberService;

@Controller
public class FindAll_DeleteController {

	final static Logger log = LoggerFactory.getLogger(FindAll_DeleteController.class);
	
	MemberService memberService;

//	@Autowired
	public FindAll_DeleteController(MemberService memberService) {
		this.memberService = memberService;
		
	}

	@RequestMapping(value="/members", method= {RequestMethod.GET, RequestMethod.DELETE})
	public String findAll(Model model) {
		model.addAttribute("memberList", memberService.findAll());
		return "showAllMembers";
	}
	
	@DeleteMapping("/members/{id}")
	public String deleteById(@PathVariable(value="id", required = false ) Integer id) {
		memberService.deleteById(id);
		return "forward:/members";
	}
	
}
