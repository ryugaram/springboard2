package com.ryu.service;

import java.util.List;

import com.ryu.domain.BoardVO;

public interface BoardService {

	public List<BoardVO> list();

	public void write(BoardVO vo);
	
	public BoardVO view(int bno);

	public void modify(BoardVO vo);
	
	public void delete(int bno);
	
	public int count();
	
	public List listpage(int displayPost, int postNum);
}
