//package com.ispan.eeit.dao.impl;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//
//import com.ispan.eeit.dao.EmployeeDao;
//import com.ispan.eeit.model.Employee;
//
//@Repository
//public class EmployeeDaoImpl implements EmployeeDao {
//
//	@PersistenceContext
//	EntityManager  entityManager;
//	
//	@Override
//	public void resetEmployeeTable() {
//		throw new RuntimeException("本系統未提供此功能");
//	}
//
//	@Override
//	public void save(Employee employee) {
//		entityManager.persist(employee);
//	}
//
//	@Override
//	public void update(Employee employee) {
//		entityManager.merge(employee);
//	}
//
//	@Override
//	public Employee findByEmployeeId(String employeeId) {
//		Employee result = null;
//		String hql = "FROM Employee WHERE employeeId = :eid";
//		List<Employee>  employees = entityManager.createQuery(hql, Employee.class)
//			                           			 .setParameter("eid", employeeId)
//		                           			 	 .getResultList();
//		if (employees.size() > 0) {
//			result = employees.get(0);
//		}
//		return result;
//	}
//	
//	@Override
//	public Employee findById(Integer id) {
//		Employee result = entityManager.find(Employee.class, id);
//		return result;
//	}
//
//	@Override
//	public List<Employee> findAll() {
//		String hql = "FROM Employee";
//		List<Employee>  employees = entityManager.createQuery(hql, Employee.class)
//				                                 .getResultList();
//		return employees;
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		String hql = "DELETE FROM Employee e WHERE e.id = :id";
//		entityManager.createQuery(hql)
//		             .setParameter("id", id)
//		             .executeUpdate();
//	}
//
//	@Override
//	public void detach(Employee employee) {
//		entityManager.detach(employee);
//	}
//
//	@Override
//	public boolean isPersist(Employee employee) {
//		// 判斷參數employee是否為entityManager控管的永續物件
//		boolean ans = entityManager.contains(employee); 
//		if (ans)
//		   return true;
//		else 
//			return false;
//	}
//}
