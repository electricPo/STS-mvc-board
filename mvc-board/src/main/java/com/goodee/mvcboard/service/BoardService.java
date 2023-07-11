package com.goodee.mvcboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.mvcboard.mapper.BoardMapper;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper; //boardservice의 필드값을 채운다
	public List<Map<String, Object>> getLocalNameList(){
		return boardMapper.selectLocalNameList();
	}
}
