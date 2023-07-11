package com.goodee.mvcboard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.goodee.mvcboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/boardList")
	public String boardList(Model model) { //Model 타입을 쓰는 이유 -> 리퀘스트와 생명주기를 같이 한다
		List<Map<String, Object>> localNameList = boardService.getLocalNameList();
		model.addAttribute("localNameList",localNameList); //request setAttribute()와 비슷한 역할
		return "/board/boardList";
	}
}
