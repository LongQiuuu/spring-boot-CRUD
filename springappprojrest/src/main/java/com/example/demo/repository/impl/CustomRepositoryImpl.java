package com.example.demo.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Member;
import com.example.demo.repository.CustomRepository;
@Repository
public class CustomRepositoryImpl implements CustomRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void detach(Member member) {
		em.detach(member);
	}

	
	
	

}
