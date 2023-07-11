package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;

@Mapper
public interface BoardMapper {
	//local_name컬럼과 count() 반환 -> 글자, 숫자 리스트
	List<Map<String, Object>> selectLocalNameList(); //추상메서드
	//마이바티스 메서드는 매개값을 하나만 허용
	//param: Map<String, Object>map -->int beginRow, int rowPerPage
	List<Board> selectBoardListByPage(Map<String, Object>map);
	//전체 행의 수
	int selectBoardCount();
}
