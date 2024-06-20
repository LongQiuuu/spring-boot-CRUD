package com.ispan.eeit.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ispan.eeit.dao.EmployeeRepositoryCustom;
import com.ispan.eeit.model.Employee;

@Repository
public class EmployeeRepositoryCustomerImpl implements EmployeeRepositoryCustom {

	@PersistenceContext
	EntityManager  entityManager;

	@Override
	public void detach(Employee employee) {
		entityManager.detach(employee);
	}

	@Override
	public boolean isPersist(Employee employee) {
		// 判斷參數employee是否為entityManager控管的永續物件
		boolean ans = entityManager.contains(employee); 
		if (ans)
		   return true;
		else 
			return false;
	}
}
