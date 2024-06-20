package com.infotran.springboot.service;

import java.util.List;
import java.util.Optional;

import com.infotran.springboot.model.CustomerBean;

public interface CustomerService {
	Optional<CustomerBean> findById(Integer id);

	List<CustomerBean> findAll();

	CustomerBean save(CustomerBean bean);
	
	CustomerBean update(CustomerBean bean); 

	void deleteById(Integer key);
	
}
