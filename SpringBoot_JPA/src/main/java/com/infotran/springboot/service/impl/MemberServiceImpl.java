package com.infotran.springboot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infotran.springboot.model.Member;
import com.infotran.springboot.repository.MemberRepository;
import com.infotran.springboot.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	final static Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	MemberRepository memberRepository;
	
//	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public void save(Member member) {
		log.info("=====>MemberRepository#save()");
		memberRepository.save(member);
	}
	
	@Override
	public List<Member> findAll() {
		log.info("=====>MemberRepository#findAll()");
		return memberRepository.findAll();
	}
	
	@Override
	public Member findById(Integer id) {
		log.info("=====>MemberRepository#findById()");
//		Optional<Member> opt = memberRepository.findById(id); 
		Member member = memberRepository.findById(id); 
		return member;
	}
	
	@Override
	public void deleteById(Integer id) {
		log.info("=====>MemberRepository#deleteById()");
		memberRepository.deleteById(id);
	}
	
	@Override
	public Member update(Member member) {
		log.info("=====>MemberRepository#update()");
		return memberRepository.update(member);
		//return memberRepository.save(member);
	}
	
	@Override
	public Member findByMemberId(String memberId) {
		log.info("=====>MemberRepository#findByMemberId()");
		return memberRepository.findByMemberId(memberId);
	}

	@Override
	public void detach(Member member) {
		log.info("=====>MemberRepository#findByMemberId()");
		memberRepository.detach(member);
	}
}
