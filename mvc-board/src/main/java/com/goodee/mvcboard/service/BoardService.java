package com.goodee.mvcboard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;
//dao 반환 값을 가공한다
@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper; //boardservice의 필드값을 채운다
	@Autowired
	private BoardfileMapper boardfileMapper;
	//게시글 상세보기
	public Board boardOne(Board board) {
		
		
		return boardMapper.boardOne(board);
	}
	
	//게시글 수정
	public int modifyBoard(Board board) {
		return boardMapper.modifyBoard(board);
	}
	
	//게시글 삭제
	public int removeBoard(Board board) {
		return boardMapper.removeBoard(board);
	}
	
	//게시글입력
	//업로드 된 파일도 받는다
	public int addBoard(Board board, String path) {
		int row = boardMapper.insertBoard(board);
		
	//파일첨부	
		//보드 입력에 성공하고 첨부된 파일이 하나 이상이 있다면
		List<MultipartFile>filelist=board.getMultipartFile();
		if(row == 1 && filelist != null && filelist.size()>0) {
			int boardNo = board.getBoardNo();
			
			//Boardfile은 addboard에서 쓰이는 지역객체, 여러개가 만들어지기도 하니까 주입하지 않고 new 연산자로 만든다
			//Boardfile boardfile = new Boardfile();
			//boardfileMapper.insertBoardfile(boardfile);
			
			for(MultipartFile mf : filelist) {
				Boardfile bf = new Boardfile();
				bf.setBoardNo(boardNo); //부모 키값
				bf.setOriginFilename(mf.getOriginalFilename()); //파일원본이름
				bf.setFilesize(mf.getSize());//파일사이즈
				bf.setFiletype(mf.getContentType());//파일타입
				//저장-> 자동으로 정적으로 저장
				//동적위치를 쓰다면 컨트롤러에서 구현한다
				//->매개값에 컨트롤러에서 구해진 실제 realpath 위치가 필요함
				
				//저장할 파일이름
				//확장자
				String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
				
				//새로운 이름 + 위에서 구한 확장자
				bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
				//테이블에 저장
				boardfileMapper.insertBoardfile(bf);
				//파일 저장 (저장위치필요 1. 정적표기 2. 컨트롤러에서 realpath 이용)
				//path 생성(컨트롤러)
				//빈파일 생성
				File f = new File(path+bf.getSaveFilename());
				//예외처리 (1. 던지기(x -> 트랜잭션 작동 안됨) 2. try catch)
				try {
					//path위치에 첨부된 파일의 스트링 주입
					mf.transferTo(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					//트랜잭션 작동을 위해 예외(try catch를 강요하지 않는 예외 -> runtimeExeption)를 발생시켜야 한다
					throw new RuntimeException();
					
				}
			}
		}

		return row;
	}
	//페이징
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName){
		
		//service layer의 역할 1 : 컨트롤러가 넘겨준 매개값을 dao의 매개값에 맞게 가공한다
		int beginRow = (currentPage-1)*rowPerPage;
		//반환값 1
		List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
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
