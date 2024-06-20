package com.ispan.eeit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.eeit.model.Employee;
import com.ispan.eeit.service.EmployeeService;
@Controller
public class FindEmployeeController extends AbstractController {
	
	Logger log = LoggerFactory.getLogger(FindEmployeeController.class);
	
	EmployeeService  employeeService;
	
	public FindEmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	@GetMapping("/ShowEmployeesByPage")
	public String sendEmployeePage() {
		return "/ShowEmployeesByPageAjax";    
	}
	
	
	@GetMapping("/employee")
	public String findAll(Model model) {
		List<Employee>  employees = employeeService.findAll();
		model.addAttribute(employees);     // 使用預設的識別字串 "employeeList"
		log.info("/employeesdjpa, 送出所有員工資料供showAllEmployees.jsp顯示");
		// 下一行對應                       /WEB-INF/views/showAllEmployees.jsp
		return "showAllEmployees";    
	}
	@GetMapping(value = "/pagingEmployeeData.json", produces = { "application/json; charset=UTF-8" })
	public @ResponseBody Map<String, Object> getPageEmployee(
		@RequestParam(value="pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value="totalPage", required = false) Integer totalPage) {
		
		if (totalPage == null) {     		
	       	totalPage = employeeService.getTotalPages();
	    }
		
		Map<String, Object> map = new HashMap<>();

		List<Employee> employees = employeeService.getPageEmployees(pageNo);
		map.put("employees", employees);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);

		return map;
	}
}
