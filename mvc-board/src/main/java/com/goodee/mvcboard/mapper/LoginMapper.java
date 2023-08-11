package com.goodee.mvcboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.goodee.mvcboard.vo.Member;

@Mapper
public interface LoginMapper {
	//로그인
	Member selectMemberById(Member member);
    
}