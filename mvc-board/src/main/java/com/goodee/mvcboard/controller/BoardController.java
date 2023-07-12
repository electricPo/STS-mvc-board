package com.goodee.mvcboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	//게시글 상세 보기
	@GetMapping("/board/boardOne")
	public String boardOne(Model model, @RequestParam(value = "boardNo") int boardNo) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		board = boardService.boardOne(board);
		
		model.addAttribute("board", board);
		return "/board/boardOne";
	}
	
	
	//폼
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return"/board/addBoard";
	}
	
	//액션
	//커맨드 객체
	@PostMapping("/board/addBoard")
	public String addBoard(Board board) {
		int row = boardService.addBoard(board);
		log.debug("\u001B[46m" + "row :" + row + "\u001B[0m");
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/boardList")
	//@RequestParam 형변환, null값 선언 안해도 된다
	public String boardList(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage
										,@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage
										,@RequestParam(name = "localName" , required = false) String localName) { //Model 타입을 쓰는 이유 -> 리퀘스트와 생명주기를 같이 한다
		System.out.println("\u001B[46m" + "localName :" + localName + "\u001B[0m");
		
		Map<String, Object> resultMap = boardService.getBoardList(currentPage, rowPerPage, localName);
		
		//view로 넘길 때는 다시 분리해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("localName", resultMap.get("localName"));
		model.addAttribute("currentPage", resultMap.get("currentPage"));
		return "/board/boardList";
	}
}
