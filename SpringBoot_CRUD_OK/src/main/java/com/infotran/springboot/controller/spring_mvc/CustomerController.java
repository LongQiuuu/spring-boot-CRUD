package com.infotran.springboot.controller.spring_mvc;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.infotran.springboot.model.CustomerBean;
import com.infotran.springboot.service.CustomerService;
import com.infotran.springboot.validate.CustomerValidator;

@Controller
@RequestMapping("/_01_customer")
public class CustomerController {

	ServletContext context;

	CustomerService service;

	@Autowired
	public CustomerController(ServletContext context, CustomerService service) {
		super();
		this.context = context;
		this.service = service;
	}

	// 查詢單筆紀錄(送出含會員資料的表單以供修改)
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// 查詢單筆紀錄(送出含會員資料的表單以供修改)
	@GetMapping("/customers/{id}")
	public String getCustomerForm(Model model, @PathVariable Integer id) {
		Optional<CustomerBean> optional = service.findById(id);
		CustomerBean bean = optional.get();
		bean.setPassword1(bean.getPassword());
		model.addAttribute("customerBean", bean);
		return "_01_customer/EditCustomerForm";
	}

	// 修改客戶資料
	@PutMapping("/customers/{id}")
	public String modifyCustomerData(@ModelAttribute("customerBean") CustomerBean bean, BindingResult bindingResult) {
		new CustomerValidator().validate(bean, bindingResult);

		if (bindingResult.hasErrors()) {
//				List<ObjectError> list = bindingResult.getAllErrors();
//				for(ObjectError error : list) {
//					System.out.println(error);
//				}
//				System.out.println("當表單資料有誤時，bean==>" + bean);
			return "_01_customer/EditCustomerForm";
		}

		service.update(bean);
		return "redirect:/_01_customer/customers";
	}

	// 新增客戶資料
	@PostMapping("/customers")
	public String insertCustomer(@ModelAttribute CustomerBean bean, BindingResult bindingResult) {
		new CustomerValidator().validate(bean, bindingResult);
//		System.out.println("新增客戶: " + bean);

		if (bindingResult.hasErrors()) {
			System.out.println("======================");
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError error : list) {
				System.out.println("有錯誤：" + error);
			}
			System.out.println("======================");
			return "_01_customer/InsertCustomerForm";
		}
		if (bean.getCustomerId() != null) {
			service.update(bean);
		}
		bean.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		service.save(bean);
		return "redirect:/_01_customer/customers";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, ce);
		// java.sql.Date
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true);
		binder.registerCustomEditor(java.sql.Date.class, ce2);
	}

	@ModelAttribute
	public CustomerBean editCustomerBean(@RequestParam(value = "customerId", required = false) Integer id) {
		CustomerBean cbean = new CustomerBean();
		if (id != null) {
			cbean = service.findById(id).get();
			System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，讀到物件:" + cbean);
		} else {
			System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，無法讀取物件:" + cbean);
		}
		return cbean;
	}
}
