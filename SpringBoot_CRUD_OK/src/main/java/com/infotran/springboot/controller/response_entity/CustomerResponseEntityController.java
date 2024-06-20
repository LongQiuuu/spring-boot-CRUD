package com.infotran.springboot.controller.response_entity;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.infotran.springboot.model.CustomerBean;
import com.infotran.springboot.service.CustomerService;

@Controller("cutomerResponseEntityController")
@RequestMapping("/_01_customerRE")
public class CustomerResponseEntityController {

	ServletContext context;
	
	CustomerService service;

//	@Autowired
	public CustomerResponseEntityController(ServletContext context, CustomerService service) {
		this.context = context;
		this.service = service;
	}

	//  查詢所有會員資料
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerBean>> getCustomers() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	// 查詢單筆紀錄(送出含會員資料的表單以供修改)	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerBean> getCustomer(@PathVariable Integer id) {
		CustomerBean bean  = null;
		ResponseEntity<CustomerBean> re = null;
		Optional<CustomerBean> optional = service.findById(id);
		if (optional.isPresent()) {
			bean = optional.get();
			bean.setPassword1(bean.getPassword());
			re = new ResponseEntity<>(bean, HttpStatus.OK);
		} else {
			re = new ResponseEntity<>(bean, HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	// 修改客戶資料
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerBean> modifyCustomerData(
			@RequestBody CustomerBean bean 
			, BindingResult bindingResult 
			) {
		System.out.println("修改會員資料");		
			CustomerBean updatedBean = service.update(bean);
			ResponseEntity<CustomerBean> re = new ResponseEntity<>(updatedBean, HttpStatus.OK);
			return re;
	}
	
	// 新增客戶資料
	@PostMapping("/customers")
	public ResponseEntity<CustomerBean> insertCustomerData(
		@RequestBody CustomerBean bean 
		, BindingResult bindingResult 
		) {
		System.out.println("新增會員資料");
		if (bean.getCustomerId() != null ) {
			service.update(bean);	
		} 
		bean.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		CustomerBean savedBean = service.save(bean);
		ResponseEntity<CustomerBean> re = new ResponseEntity<>(savedBean, HttpStatus.OK);
		return re;
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<CustomerBean> deleteCustomerData(@PathVariable Integer id) {
		Optional<CustomerBean> optional = service.findById(id);
		System.out.println("刪除會員資料");		
		if (!optional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		service.deleteById(id);	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
	
	@ModelAttribute
	public CustomerBean editCustomerBean(@RequestParam(value="customerId", required = false) Integer id) {
		CustomerBean cbean = new CustomerBean();
		if (id != null) {
			cbean = service.findById(id).get();
//			System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，讀到物件:" + cbean);
		} else {
//			System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，無法讀取物件:" + cbean);
		}
		return cbean;
	}
}
