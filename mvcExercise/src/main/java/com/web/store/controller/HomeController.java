package com.web.store.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.web.store.model.BookBean;

//要加這個才能讓系統知道這是一個Bean
@Controller
public class HomeController {
	
	private static Logger Log =LoggerFactory.getLogger( HomeController .class);
	
	//掃描請求路徑 / 掃到welcome 就會執行下列方法
	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("title", "歡迎蒞臨君雅網路商城!!!");
		model.addAttribute("subtitle", "網路上獨一無二的購物天堂");
		//視圖的邏輯名稱
		return "welcome";
	}
	
	
	//掃描請求路徑 
	@GetMapping("/")
	public String home(Model model,HttpServletRequest request) {
		//設定時間格式
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		Date d =new Date();
		String now =sdf.format(d);
		Date d1 =new Date(Long.MAX_VALUE);
		String max =sdf.format(d1);
		//HttpServletRequest request 帶參數方法
		String ContextPath=request.getContextPath();
		//將時間渲染到網頁上
		model.addAttribute("nowAAA", now);
		model.addAttribute("maxbbb", max);
		//HttpServletRequest request帶參數方法
		model.addAttribute("conPath", ContextPath);
		//視圖的邏輯名稱
		return "index";
	}
	
	@ModelAttribute("kitty")//每次请求处理方法被调用之前执行
	 public BookBean abcMethod() {
	  Log.info("執行HomeController");
	  BookBean bookBean = new BookBean();
	  return bookBean; //自動成為屬性物件
	 }
}
