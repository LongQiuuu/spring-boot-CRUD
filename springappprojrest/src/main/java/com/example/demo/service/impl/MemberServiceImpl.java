package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Member;
import com.example.demo.repository.CustomRepository;
import com.example.demo.repository.MemberSDJpaRepository;
import com.example.demo.service.MemberService;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	
	final static Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	MemberSDJpaRepository memberRepository;
	
	CustomRepository customRepository;
	
//	@Autowired
	public MemberServiceImpl(MemberSDJpaRepository memberRepository, CustomRepository customRepository) {
		this.memberRepository = memberRepository;
		this.customRepository = customRepository;
	}

	@Override
	public void save(Member member) {
		log.info("=====>MemberServiceImpl#save()");
		memberRepository.save(member);
	}
	
	@Override
	public List<Member> findAll() {
		log.info("=====>MemberServiceImpl#findAll()");
		return memberRepository.findAll();
	}
	
	@Override
	public Member findById(Integer id) {
		Member member = null;
		log.info("=====>MemberServiceImpl#findById()");
		Optional<Member> opt = memberRepository.findById(id); 
		if (opt.isPresent()) {
			member = opt.get();
		} else throw new ResourceNotFoundException("會員(鍵值=" + id + ")不存在");
			
		return member;
	}
	
	@Override
	public void deleteById(Integer id) {
		log.info("=====>MemberServiceImpl#deleteById()");
		memberRepository.deleteById(id);
	}
	
	@Override
	public Member update(Member member) {
		log.info("=====>MemberServiceImpl#update()");
		//return memberRepository.update(member);
		return memberRepository.save(member);
	}
	
	@Override
	public Member findByMemberId(String memberId) {
		log.info("=====>MemberServiceImpl#findByMemberId()");
		return memberRepository.findByMemberId(memberId);
	}

	@Override
	public void detach(Member member) {
		log.info("=====>MemberServiceImpl#findByMemberId()");
		customRepository.detach(member);
	}

	@Override
	public String checkMemberId(String memberId) {
		String id = "";
		Member member = memberRepository.findByMemberId(memberId);
		if (member != null) {
			id = member.getMemberId();
		}
		return id;
	}
}
