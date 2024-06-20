package com.infotran.springboot.controller.spring_mvc;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infotran.springboot.model.CustomerBean;
import com.infotran.springboot.service.CustomerService;

@Controller
@RequestMapping("/_01_customer")
public class CustomerQueryController {

	ServletContext context;

	CustomerService service;
	
	@Autowired
	public CustomerQueryController(ServletContext context, CustomerService service) {
		super();
		this.context = context;
		this.service = service;
	}
	
	// 查詢所有會員資料
	@GetMapping("/customers")
	public String findAll(Model model) {
		List<CustomerBean> beans = service.findAll();
		model.addAttribute(beans);      
		// 若屬性物件為CustomerBean型別的物件，則預設的識別字串 ==> customerBean
		// 若屬性物件為List<CustomerBean>型別的物件，則預設的識別字串 ==> customerBeanList
		return "_01_customer/ShowCustomers";
	}
	
	// 本方法送出新增Customer資料的空白表單
	@GetMapping("/insertCustomer")
	public String showCustomerEmptyForm(Model model) {
		CustomerBean bean = new CustomerBean();
		model.addAttribute("customerBean", bean);
		return "_01_customer/InsertCustomerForm";
	}
	
	@DeleteMapping("/customers/{id}")
	public String deleteCustomerData(@PathVariable Integer id) {
		service.deleteById(id);	
		return "redirect:/_01_customer/customers";
	}
	
	@RequestMapping("/index")
	public String home() {
		return "_01_customer/index";
	}

}
