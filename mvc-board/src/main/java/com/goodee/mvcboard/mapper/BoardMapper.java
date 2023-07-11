package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	//local_name커럶과 count() 반환 -> 글자, 숫자 리스트
	List<Map<String, Object>> selectLocalNameList(); //추상메서드
}
