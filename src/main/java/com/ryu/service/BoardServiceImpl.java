package com.ryu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ryu.dao.BoardDAO;
import com.ryu.domain.BoardVO;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	
	@Override
	public List<BoardVO> list() {
		
		return dao.list();
	}
	
	@Override
	public void write(BoardVO vo) {
		dao.write(vo);
	}

	@Override
	public BoardVO view(int bno) {
		
		return dao.view(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		
		dao.modify(vo);
		
	}

	@Override
	public void delete(int bno) {
		
		dao.delete(bno);
		
	}

	@Override
	public int count() {
		
		return dao.count();
	}

	@Override
	public List listpage(int displayPost, int postNum) {
		 return dao.listpage(displayPost, postNum);
	}
	
	

}
