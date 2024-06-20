package com.ispan.eeit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit.model.Employee;

public interface EmployeeRepository 
    extends JpaRepository<Employee, Integer> {
	
	Employee findByEmployeeId(String employeeId);
	
	// void resetEmployeeTable();
	
//	Optional<Employee> save(Employee employee);
//	
//	void deleteById(Integer id);
//
//	void update(Employee employee);
//	
//	Employee findByEmployeeId(String employeeId);
//	
//	Employee findById(Integer id);
//	
//	List<Employee> findAll();
//	
	@Override
	boolean existsById(Integer id);
//
//	void detach(Employee employee);
	
}
