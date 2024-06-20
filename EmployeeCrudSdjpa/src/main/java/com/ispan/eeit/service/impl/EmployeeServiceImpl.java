package com.ispan.eeit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ispan.eeit.dao.EmployeeRepository;
import com.ispan.eeit.dao.EmployeeRepositoryCustom;
import com.ispan.eeit.model.Employee;
import com.ispan.eeit.service.EmployeeService;
import com.ispan.eeit.utils.SystemService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	EmployeeRepository employeeDao;
	
	EmployeeRepositoryCustom employeeRepositoryCustom;
	
//	@Autowired
	EmployeeServiceImpl(EmployeeRepository employeeDao, EmployeeRepositoryCustom employeeRepositoryCustom) {
		super();
		this.employeeDao = employeeDao;
		this.employeeRepositoryCustom = employeeRepositoryCustom;
	}

	@Override
	public void resetEmployeeTable() {
		throw new RuntimeException("本系統未提供此功能");
	}


	@Override
	public void save(Employee employee) {
		
		employeeDao.save(employee);
	}

	@Override
	public void update(Employee employee) {
//		employeeDao.update(employee);
		employeeDao.save(employee);
	}

	@Override
	public Employee findByEmployeeId(String employeeId) {
		Employee emp = employeeDao.findByEmployeeId(employeeId);
		return emp;
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		return employeeDao.findById(id);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		employeeDao.deleteById(id);
	}

	@Override
	public boolean existsByEmployeeId(Employee employee) {
		if (employeeRepositoryCustom.isPersist(employee)) {
			employeeRepositoryCustom.detach(employee);
		}

		Employee emp = findByEmployeeId( employee.getEmployeeId() );
		return  emp != null;
	}

	@Override
	public boolean isPersist(Employee employee) {
		boolean ans = employeeDao.existsById(employee.getId());
		return ans;
	}

	@Override
	public void detach(Employee employee) {
		employeeRepositoryCustom.detach(employee);
	}

	@Override
	public List<Employee> getPageEmployees(Integer pageNo) {
		
//		log.info("in EmployeeService, 讀取一頁員工資料之 pageNo=" + pageNo);
		int startPageNo = pageNo - 1;
//		Pageable pageable = PageRequest.of(startPageNo, GlobalService.RECORDS_PER_PAGE);
		Pageable pageable = PageRequest.of(startPageNo, SystemService.RECORDS_PER_PAGE, Sort.by("id").ascending());
		Page<Employee> page = employeeDao.findAll(pageable);
		List<Employee> pageEmployees = page.getContent();
		
		return pageEmployees;
	}

	@Override
	public Integer getTotalPages() {
		long count = employeeDao.count();
		int totalPages = (int) (Math.ceil(count / (double) SystemService.RECORDS_PER_PAGE));
		return totalPages;
	}

}
