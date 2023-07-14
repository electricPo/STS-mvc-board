package com.goodee.mvcboard.restapi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.mvcboard.service.BoardService;

//기존 컨트롤러는 view 반환
//cross브라우저 제한 x -> open rest api
//ajax - 자바스크립트 객체(원하는 형태로)
@CrossOrigin
@RestController
public class BoardRest {
	@Autowired
	private BoardService boardService;
	@GetMapping("/rest/localNameList")
	public List<Map<String, Object>>getLocalNameList(){
		return boardService.getLocalNameList();
	}
}
