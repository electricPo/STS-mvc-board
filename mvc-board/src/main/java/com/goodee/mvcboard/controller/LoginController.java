
package com.goodee.mvcboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.goodee.mvcboard.service.LoginService;
import com.goodee.mvcboard.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	@Autowired private LoginService loginService;
	
	//로그인 페이지로 이동
	@GetMapping("/login")
	public String login(HttpServletRequest request) {
		return "/login";
	}
	//로그인 액션
	@PostMapping("/login")
	public String login(HttpServletRequest request,
						HttpServletRequest response,
						@RequestParam(name= "id") String id,
						@RequestParam(name ="pw") String pw) {
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		Member loginMember = loginService.login(member);
		//로그인 실패
		if(loginMember == null) {
			log.debug("로그인 실패");
			return "redirect:/login";
		}
		//로그인 성공 : session 에 로그인 정보 저장
		log.debug("로그인 성공");
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", loginMember);

		return "redirect:/board/boardList";
		
		
		}
	//로그아웃 액션
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/login";
	}
}