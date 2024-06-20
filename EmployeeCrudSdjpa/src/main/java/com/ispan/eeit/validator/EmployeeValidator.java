package com.ispan.eeit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ispan.eeit.model.Employee;
import com.ispan.eeit.service.EmployeeService;

@Component
public class EmployeeValidator implements Validator {

	EmployeeService employeeService; 
	
//	@Autowired
	EmployeeValidator(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Employee.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "employeeId", "employee.employeeId.empty.error", "必須輸入員工編號"); 
		ValidationUtils.rejectIfEmpty(errors, "name", "employee.name.empty.error", "必須輸入姓名");
		ValidationUtils.rejectIfEmpty(errors, "birthday", "employee.birthday.empty.error", "必須輸入生日");
		ValidationUtils.rejectIfEmpty(errors, "email", "employee.email.empty.error", "必須輸入電郵地址");
		Employee employee = (Employee)target;
		
		if (employee.getImage() == null || employee.getImage().length() == 0) {
			if (employee.getFilename().length() == 0) {
				errors.rejectValue("filename", "employee.image.empty.error", "必須挑選員工照片");				
			}
		}
		
	}

	

}
