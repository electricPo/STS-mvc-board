package com.goodee.mvcboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Mapper
public interface BoardfileMapper {
	//게시판에 파일을 추가
	int insertBoardfile(Boardfile boardfile);
	
	List<Boardfile> selectBoardfileOne(Board board);
}
