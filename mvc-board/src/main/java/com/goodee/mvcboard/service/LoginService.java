package com.goodee.mvcboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.mvcboard.mapper.LoginMapper;
import com.goodee.mvcboard.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class LoginService {
	@Autowired
	private LoginMapper loginMapper;
	
	//로그인
	public Member login(Member member) {
		Member loginMember = loginMapper.selectMemberById(member);
		log.debug(loginMapper +"<-LoginService");
		return loginMember;
	}
}