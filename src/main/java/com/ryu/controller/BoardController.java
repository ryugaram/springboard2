package com.ryu.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.domain.BoardVO;
import com.ryu.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String getList(Model model) {
		List<BoardVO> list=null;
		list=service.list();
		model.addAttribute("list",list);
		return "board/list";
	}
	
	
	@RequestMapping(value = "/write",method =  RequestMethod.GET)
	public void getWrite() {
}
	
	@RequestMapping(value = "/write",method = RequestMethod.POST)
	public String PostWrite(BoardVO vo) {
	service.write(vo);
	
	return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/view",method=RequestMethod.GET)
	public void getView(@RequestParam("bno")int bno,Model model){
		
			BoardVO vo=service.view(bno);
			
			model.addAttribute("view",vo);
		
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno, Model model){

	 BoardVO vo = service.view(bno);
	   
	 model.addAttribute("view", vo);
	}
	
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(BoardVO vo) {

	 service.modify(vo);
	   
	 return "redirect:/board/view?bno=" + vo.getBno();
	}
	
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public String getDelete(int bno) {
	
		service.delete(bno);
		
		return "redirect:/board/list";
	}
	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listpage", method = RequestMethod.GET)
	public void getListPage(Model model) {
	  
	 List list = null; 
	 list = service.list();
	 model.addAttribute("list", list);   
	}
	
	
	@RequestMapping(value = "/listpage2", method = RequestMethod.GET)
	public void getListPage(Model model, @RequestParam("num") int num) {
	 
	 // 게시물 총 갯수
	 int count = service.count();
	  
	 // 한 페이지에 출력할 게시물 갯수
	 int postNum = 10;
	  
	 // 하단 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
	 int pageNum = (int)Math.ceil((double)count/postNum);
	  
	 // 출력할 게시물
	 int displayPost = (num - 1) * postNum;
	 
	 
	// 한번에 표시할 페이징 번호의 갯수
	 int pageNum_cnt = 10;

	 // 표시되는 페이지 번호 중 마지막 번호
	 int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);

	 // 표시되는 페이지 번호 중 첫번째 번호
	 int startPageNum = endPageNum - (pageNum_cnt - 1);
	 
	// 마지막 번호 재계산
	 int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
	  
	 if(endPageNum > endPageNum_tmp) {
	  endPageNum = endPageNum_tmp;
	 }
	 boolean prev = startPageNum == 1 ? false : true;
	 boolean next = endPageNum * pageNum_cnt >= count ? false : true;
	    
	 List list = null; 
	 list = service.listpage(displayPost, postNum);
	 model.addAttribute("list", list);   
	 model.addAttribute("pageNum", pageNum);
	 
	// 시작 및 끝 번호
	 model.addAttribute("startPageNum", startPageNum);
	 model.addAttribute("endPageNum", endPageNum);

	 // 이전 및 다음 
	 model.addAttribute("prev", prev);
	 model.addAttribute("next", next);
	 
	 model.addAttribute("select", num);
	}
	}


