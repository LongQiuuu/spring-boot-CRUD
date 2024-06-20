package com.ispan.eeit.dao;

import com.ispan.eeit.model.Employee;

public interface EmployeeRepositoryCustom {
	
	public boolean isPersist(Employee employee);
	
	void detach(Employee employee);
}
