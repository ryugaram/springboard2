package com.ryu.dao;

import java.util.List;

import com.ryu.domain.BoardVO;

public interface BoardDAO {

	public List<BoardVO> list();
	
	public void write(BoardVO vo);

	public BoardVO view(int bno);
	
	public void modify(BoardVO vo);
	
	public void delete(int bno);
	
	public int count();
	
	public List listpage(int displayPost, int postNum);

	
}
