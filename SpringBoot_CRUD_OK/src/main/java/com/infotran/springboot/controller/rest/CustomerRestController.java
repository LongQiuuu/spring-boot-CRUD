package com.infotran.springboot.controller.rest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.infotran.springboot.model.CustomerBean;
import com.infotran.springboot.service.CustomerService;
import com.infotran.springboot.validate.CustomerValidator;

@RestController
@RequestMapping("/_01_customerRB")
public class CustomerRestController {

	ServletContext context;

	CustomerService service;

//	@Autowired
	public CustomerRestController(ServletContext context, CustomerService service) {
		this.context = context;
		this.service = service;
	}

	// 查詢單筆紀錄
	@GetMapping("/customers/{id}")
	public CustomerBean editCustomerForm(@PathVariable Integer id, HttpServletResponse response) {
		return findById(id, response);
	}

	// 查詢所有會員資料
	@GetMapping("/customers")
	public List<CustomerBean> findAll() {
		return service.findAll();
	}

	// 查詢單筆紀錄(送出含會員資料的表單以供修改)
	@GetMapping("/customersForm/{id}")
	public CustomerBean findById(@PathVariable Integer id, HttpServletResponse response) {
		CustomerBean bean = null;
		Optional<CustomerBean> optional = service.findById(id);
		if (optional.isPresent()) {
			bean = optional.get();
			bean.setPassword1(bean.getPassword());
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return bean;
	}

	// 新增客戶資料
	@PostMapping("/customers")
	public String save(@RequestBody CustomerBean bean, BindingResult bindingResult) {
		new CustomerValidator().validate(bean, bindingResult);
		System.out.println("新增客戶: " + bean);

		if (bindingResult.hasErrors()) {
//			System.out.println("======================");
//			List<ObjectError> list = bindingResult.getAllErrors();
//			for (ObjectError error : list) {
//				System.out.println("有錯誤：" + error);
//			}
//			System.out.println("======================");
			return "_01_customer/InsertCustomerForm";
		}
		if (bean.getCustomerId() != null) {
			service.update(bean);
		}
		bean.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		CustomerBean savedBean = service.save(bean);
		return String.valueOf(savedBean.getCustomerId());
	}

	// 修改客戶資料
	@PutMapping("/customers/{id}")
	public String update(@RequestBody CustomerBean bean, BindingResult bindingResult) {
		new CustomerValidator().validate(bean, bindingResult);
		if (bindingResult.hasErrors()) {
//				List<ObjectError> list = bindingResult.getAllErrors();
//				for(ObjectError error : list) {
//					System.out.println(error);
//				}
//				System.out.println("當表單資料有誤時，bean==>" + bean);
			return "_01_customer/EditCustomerForm";
		}
		CustomerBean updatedBean = service.update(bean);
		return String.valueOf(updatedBean.getCustomerId());
	}

	@DeleteMapping("/customers/{id}")
	public void deleteById(@PathVariable Integer id) {
		service.deleteById(id);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, ce);
		// java.sql.Date
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true);
		binder.registerCustomEditor(java.sql.Date.class, ce2);
	}

}
