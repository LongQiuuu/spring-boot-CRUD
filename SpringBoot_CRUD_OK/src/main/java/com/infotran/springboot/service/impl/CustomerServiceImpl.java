package com.infotran.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.infotran.springboot.dao.CustomerRepository;
import com.infotran.springboot.model.CustomerBean;
import com.infotran.springboot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Optional<CustomerBean> findById(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<CustomerBean> findAll() {
		return (List<CustomerBean>) customerRepository.findAll();
	}

	@Override
	public CustomerBean save(CustomerBean bean) {
		return customerRepository.save(bean);
	}

	@Override
	public CustomerBean update(CustomerBean bean) {
		return customerRepository.save(bean);
	}

	@Override
	public void deleteById(Integer id) {
		customerRepository.deleteById(id);
	}
}
