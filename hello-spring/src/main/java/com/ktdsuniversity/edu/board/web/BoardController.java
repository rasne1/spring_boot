package com.ktdsuniversity.edu.board.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.board.enums.ReadType;
import com.ktdsuniversity.edu.board.service.BoardService;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.SearchResultVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;

import jakarta.validation.Valid;

@Controller
public class BoardController {
	/*
	 * 빈 컨테이너에 들어있는 객체중 타입이 일치하는 객체를 할당 받는다.
	 * 
	 */

	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String viewListPage(Model model) {

		SearchResultVO searchResult = this.boardService.findAllBoard();

		// 게시글의 목록을 조회.
		List<BoardVO> list = searchResult.getResult();
		// 게시글의 개수 조회.
		int searchCount = searchResult.getCount();

		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);

		return "board/list";
	}

// 게시글 등록 화면 보여주는 endpoint 

	@GetMapping("/write")
	public String viewWritePage() {
		return "board/write";
	}

	@PostMapping("/write")
	public String doWriteAction(@Valid @ModelAttribute WriteVO writeVO,
			//valid의 결과를 받아오는 파라미터
			// 반드시 @Valid의 파라미터 이후에 작성
			BindingResult bindingResult,Model model) {
		//사용자의 입력값을 검증 했을 때, 에러가 있다면
		if(bindingResult.hasErrors()) {
			//브라우저에게 "board/write" 페이지를 보여주도록 하고
			//해당 페이지에 사용자가 입력한 값을 전달한다.
			model.addAttribute("inputData", writeVO);
			return "board/write";
		}
		
		System.out.println(writeVO.getContent());
		System.out.println(writeVO.getEmail());
		System.out.println(writeVO.getSubject());
		// create, update, delete ==> 성공/실패 여부 반환.
		boolean createResult = this.boardService.createNewBoard(writeVO);

		System.out.println("게시글 생성 어찌됬나 " + createResult);

		// redirect: 브라우저에게 다음 end point를 요청하도록 지시.
		// redirect:/ ==> 브라우저에게 "/" endpoint로 이동하도록 지시.
		return "redirect:/";
	}

//게시글 내용 조회
// endpoint ==> /view/게시글아이디 예> /view/BO-20260327-000001
// 해야 하는 역할
// 1.게시글 내용을 조회해서 브라우저에게 노출.
// 2. 조회수 1증가.

	@GetMapping("/view/{articleId}")
	public String viewDetailPage(Model model, @PathVariable String articleId) {

		// articleId로 데이터베이스에서 게시글을 조회한다.
		// 조회할 때 조회수가 하나 증가해야 한다.
		BoardVO findResult = this.boardService.findBoardByArticleId(articleId, ReadType.VIEW);

		model.addAttribute("article", findResult);

		return "board/view";
	}

	@GetMapping("/delete")
	public String doDeleteAction(@RequestParam String id) {

		boolean deletePosts = this.boardService.deletePosts(id);

		return "redirect:/";
	}

	@GetMapping("/update/{articleId}")
	public String viewUpdatePage(@PathVariable String articleId, Model model) {
		BoardVO data = this.boardService.findBoardByArticleId(articleId, ReadType.UPDATE);
		model.addAttribute("article", data);
		System.out.println(data.getId());
		return "board/update";   
	}

	@PostMapping("/update/{articleId}")
	public String doUpdateAction(@PathVariable String articleId, UpdateVO updateVO) {

		updateVO.setId(articleId);

		boolean updateResult = this.boardService.updateBoardByArticleId(updateVO);
		System.out.println("수정 성공?" + updateResult);

		return "redirect:/view/" + articleId;
	}

	/*
	 * /member/view/사용자아아디 ==> 회원 정보 조회 하기. /member/update/사용자아이디 ==> 회원 정보 수정 페이지
	 * 보기 /member/update/사용자아이디 ==> 회원정보 수정하기 /member/delete?id=사용자아이디 ==> 회원정보
	 * 삭제하기.
	 * 
	 */

}
