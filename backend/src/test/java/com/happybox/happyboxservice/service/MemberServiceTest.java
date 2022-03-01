package com.happybox.happyboxservice.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.happybox.happyboxservice.domain.Member;
import com.happybox.happyboxservice.repository.MemberRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	MemberServiceImpl memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	@DisplayName("회원가입")
	public void join() throws Exception {
		//given
		Member member = new Member.Builder().name("Lim").build();

		//when
		Long savedId = memberService.join(member);

		//then
		assertEquals(member, memberRepository.findById(savedId).orElseThrow(IllegalArgumentException::new));
	}

	@Test
	@DisplayName("중복된 이름으로 회원가입")
	public void joinDuplicated() throws Exception{
	    //given
		Member member1 = new Member.Builder().name("Lim").build();
		Member member2 = new Member.Builder().name("Lim").build();

	    //when
		memberService.join(member1);

	    //then
		assertThrows(IllegalArgumentException.class,
			() -> memberService.join(member2));

	}
}