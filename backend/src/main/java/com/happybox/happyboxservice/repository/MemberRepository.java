package com.happybox.happyboxservice.repository;

import java.util.List;
import java.util.Optional;

import com.happybox.happyboxservice.domain.Member;

public interface MemberRepository {
	void save(Member member);

	Optional<Member> findById(Long id);

	Optional<Member> findByName(String name);

	List<Member> findAll();
}