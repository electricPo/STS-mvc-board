package com.goodee.mvcboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.vo.Board;
//dao 반환 값을 가공한다
@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper; //boardservice의 필드값을 채운다
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage){
		
		//service layer의 역할 1 : 컨트롤러가 넘겨준 매개값을 dao의 매개값에 맞게 가공한다
		int beginRow = (currentPage-1)*rowPerPage;
		//반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		//반환값 2
		List<Board>boardList = boardMapper.selectBoardListByPage(paramMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		//service layer의 역할 2 : dao에서 반환받은 값을 컨트롤러의 반환값에 맞게 가공한다
		int boardCount = boardMapper.selectBoardCount();
		int lastPage = boardCount / rowPerPage;
		if(boardCount%rowPerPage != 0) {
			lastPage +=1;
		}
		
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("currentPage", currentPage);
		resultMap.put("lastPage", lastPage);
		return resultMap;
	}
}
