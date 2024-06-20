package com.ispan.eeit.service;

import java.util.List;
import java.util.Optional;

import com.ispan.eeit.model.Employee;

public interface EmployeeService {
	void resetEmployeeTable();
	void save(Employee employee);
	void update(Employee employee);
	
	Employee findByEmployeeId(String employeeId);
	
	boolean existsByEmployeeId(Employee employee);
	
//	Employee findById(Integer id);
	Optional<Employee> findById(Integer id);
	
	List<Employee> findAll();
	
	void deleteById(Integer id);
	
	boolean isPersist(Employee employee);
	
	void detach(Employee employee);
	
	List<Employee> getPageEmployees(Integer pageNo);
	
	Integer getTotalPages();
}
