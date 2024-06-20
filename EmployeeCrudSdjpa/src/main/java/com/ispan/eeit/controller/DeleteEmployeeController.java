package com.ispan.eeit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ispan.eeit.service.EmployeeService;

// 處理刪除員工資料的控制器
@Controller
public class DeleteEmployeeController extends AbstractController{
    
	Logger log = LoggerFactory.getLogger(DeleteEmployeeController.class);
	
	EmployeeService  employeeService;
	
//	@Autowired
    public DeleteEmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	// 使用@DeleteMapping來處理 HTTP DELETE請求
    // 1. RedirectAttributes的 addFlashAttribute()方法可以在不同請求中傳遞訊息，
    //    此訊息一經顯示就會由 HttpSession自動移除
    // 2. 為了符合REST風格，欲刪除之紀錄的主鍵由PathVariable來傳遞
    // 3. 若由其他訊息要傳送可以使用請求參數(Request Parameter)來傳送 
	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(RedirectAttributes ra, 
			@PathVariable Integer id,
			@RequestParam String empNo
			) {
		try {
			employeeService.deleteById(id);
			ra.addFlashAttribute("message", "<font color='green'>已刪除員工編號: " +  empNo + " 之紀錄</font>");
			log.info("/employeesdjpa, 已刪除員工編號: " +  empNo + " 之紀錄");
		} catch (Exception e) {
			ra.addFlashAttribute("message", "<font color='red'>刪除員工編號: " +  empNo + " 之紀錄失敗:" + e.getMessage() + "</font>");
			log.error("/employeesdjpa, 刪除員工編號: " +  empNo + " 之紀錄失敗");
			e.printStackTrace();
		}
		return "redirect:/employee";
	}
}
