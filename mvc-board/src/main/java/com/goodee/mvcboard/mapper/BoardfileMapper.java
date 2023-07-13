package com.goodee.mvcboard.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	//게시판에 파일을 추가
	int insertBoardfile(Boardfile boardfile);
	
	int selectBoardfile(Boardfile boardfile);
}
