package com.example.demo.repository;

import com.example.demo.model.Member;

public interface CustomRepository {
	void detach(Member member);
}
