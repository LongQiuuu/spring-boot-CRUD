package com.infotran.springboot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infotran.springboot.model.Member;
import com.infotran.springboot.repository.MemberRepository;

@Repository
public class MemberRepositoryJpaImpl implements MemberRepository {
	
	// @PersistenceContext 會對每一個客戶端的請求配置一個專用的EntityManager物件。
  
	@PersistenceContext 
	EntityManager em;
	
	@Override
	public void save(Member member) {
		em.persist(member);
	}

	@Override
	public List<Member> findAll() {
		String hql = "FROM Member";
		return em.createQuery(hql, Member.class)
	             .getResultList();
	}

	@Override
	public Member findById(Integer id) {
		return em.find(Member.class, id);
	}

	@Override
	public void deleteById(Integer id) {
		Member member = em.find(Member.class, id);
		em.remove(member);
	}

	@Override
	public Member update(Member member) {
		// 如果傳入之物件沒有oid，則執行INSERT INTO SQL命令
		// 否則執行UPDATE SQL命令
		return em.merge(member);
	}

	@Override
	public Member findByMemberId(String memberId) {
		String hql = "FROM Member m WHERE m.memberId = :id";
		Member member = null;
		try {
			member = em.createQuery(hql, Member.class)
				       .setParameter("id", memberId).getSingleResult();
		} catch(NoResultException ex) {
			;
		} 
		return member;
	}

	@Override
	public void detach(Member member) {
		em.detach(member);
	}

}
