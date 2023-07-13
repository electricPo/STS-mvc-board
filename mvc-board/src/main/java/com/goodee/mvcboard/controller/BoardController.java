package com.goodee.mvcboard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	//게시글 삭제 폼
	@GetMapping("board/removeBoard")
	public String removeBoard(Model model,
								@RequestParam(name = "boardNo") int boardNo) {
		//board 값 셋팅
		Board board = new Board();
		board.setBoardNo(boardNo);
		board=boardService.boardOne(board);
		
		model.addAttribute("board", board);
		
		return "/board/removeBoard";
	}
	
	
	//게시글 삭제 액션
	@PostMapping("board/removeBoard")
	public String removeBoard(
							@RequestParam(name = "boardNo") int boardNo,
							@RequestParam(name = "memberId") String memberId) {
		//board 값 셋팅
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setMemberId(memberId);
				int row = boardService.removeBoard(board);
		
		return "redirect:/board/boardList";
		
	}
	
	//게시글 수정 폼
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, @RequestParam(name = "boardNo") int boardNo) {
		//boardNo 선언
		Board board = new Board();
		board.setBoardNo(boardNo);
		board=boardService.boardOne(board);
		
		model.addAttribute("board", board);
		return"/board/modifyBoard";
	}
	
	//게시글 수정 액션
	@PostMapping("/board/modifyBoard")

	public String modifyBoard(
								@RequestParam(name = "boardNo") int boardNo,
								@RequestParam(name = "localName") String localName,
								@RequestParam(name = "boardTitle") String boardTitle,
								@RequestParam(name = "boardContent") String boardContent,
								@RequestParam(name = "memberId") String memberId
								
	) {
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setLocalName(localName);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		board.setMemberId(memberId);
		
		int row = boardService.modifyBoard(board);

		return "redirect:/board/boardOne?boardNo=" + boardNo;
		
	}
	
	
	//게시글 상세 보기
	@GetMapping("/board/boardOne")
	public String boardOne(Model model, @RequestParam(name = "boardNo") int boardNo) {
		//boardNo 선언
		Board board = new Board();
		board.setBoardNo(boardNo);
		board = boardService.boardOne(board);
		
		model.addAttribute("board", board);
		return "/board/boardOne";
	}
	
	
	//게시글 추가 폼
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return"/board/addBoard";
	}
	
	//게시글 추가 액션
	//커맨드 객체
	@PostMapping("/board/addBoard")
	public String addBoard(HttpServletRequest request, Board board) { //매개값 리퀘스트 객체
		String path = request.getServletContext().getRealPath("/upload/");
		
		int row = boardService.addBoard(board, path);
		
		log.debug("\u001B[46m" + "File path: " + path + "\u001B[0m");
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
