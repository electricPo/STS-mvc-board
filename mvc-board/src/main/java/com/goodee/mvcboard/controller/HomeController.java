package com.goodee.mvcboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //서블렛 구현과 같다
public class HomeController {
	@GetMapping("/home") //web.xml의 url 패턴맵핑 or 애노테이션 webservlet
	public String home() {
		return "home"; // 포워드
	}
	
}
