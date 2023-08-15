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
	//로그인 요청이 들어왔을 때 입력받은 회원정보를 db에서 조회하고 일치하면 로그인 성공
	@Autowired
	//loginMapper 인터페이스 주입 (db 연동)
	private LoginMapper loginMapper;
	
	//로그인
	//login 메서드 -> 주어진 Member 객체를 파라미터로 받아 db에서 조회
	public Member login(Member member) {
		//해당 회원의 정보를 가져옴
		Member login = loginMapper.selectMemberById(member);
		log.debug(loginMapper +"<-LoginService");
		return login;
	}
}