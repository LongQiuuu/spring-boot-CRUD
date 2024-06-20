package com.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


//配合index 讀取查詢字串
//要加這個才能讓系統知道這是一個Bean
@Controller
public class DemoController {
	//qqq=100&data=1.5&name=凱蒂貓
	//(Model model ,Integer i , Double d , String catName) 建立參數來承接
	//@RequestParam('告訴他要讀什麼欄位')
	//掃描請求路徑 / 掃到welcome 就會執行下列方法
		@GetMapping("/requestParam")
		public String requestParamMethod(Model model ,
				@RequestParam("qqq") Integer i , 
				@RequestParam("data") Double d , 
				@RequestParam(value="name",defaultValue = "劉芳") String catName) {
			//defaultValue = "劉芳"指定裡面裝什麼 如果沒寫 他會抓取index.jsp裡面寫的
			System.out.println("i="+i+",i*2"+i*2);
			System.out.println("d="+d+",d*2"+d*2);
			System.out.println("name"+catName);
			
			//視圖的邏輯名稱 welcome .jsp
			return "welcome";
		}
}
