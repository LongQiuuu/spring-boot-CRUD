package com.infotran.springboot.service;

import java.util.List;

import com.infotran.springboot.model.Member;

public interface MemberService {

	void save(Member member);
	List<Member>	 findAll();
	Member	 findById(Integer id);
	Member	 findByMemberId(String memberId);
	void	 deleteById(Integer id);
	Member   update(Member member);
	void   detach(Member member);
	
}
