package com.infotran.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.infotran.springboot.model.CustomerBean;

public interface CustomerRepository 
			extends CrudRepository<CustomerBean, Integer> {

}
