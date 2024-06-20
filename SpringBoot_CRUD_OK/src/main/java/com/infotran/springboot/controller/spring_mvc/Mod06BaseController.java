package com.infotran.springboot.controller.spring_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mod06BaseController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
