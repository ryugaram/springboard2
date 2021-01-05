package com.ryu.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ryu.domain.BoardVO;


@Repository
public class BoardDAOImpl implements BoardDAO {

	
	@Inject
	private SqlSession sql;
	
	private static String namespace= "com.ryu.mappers.board";
	
	
	
	
	@Override
	public List<BoardVO> list() {
		
	
		return sql.selectList(namespace+".list");
	}




	@Override
	public void write(BoardVO vo) {
		
		sql.insert(namespace+".write",vo);
		
	}
	
	public BoardVO view(int bno) {
		return sql.selectOne(namespace+".view",bno);
	}



	@Override
	public void modify(BoardVO vo){
	 sql.update(namespace + ".modify", vo);
	}




	@Override
	public void delete(int bno) {
		
		sql.delete(namespace+".delete",bno);
		
	}




	@Override
	public int count() {
		
		return sql.selectOne(namespace + ".count"); 
		
	}




	@Override
	public List listpage(int displayPost, int postNum) {
		 HashMap data = new HashMap();
		  
		 data.put("displayPost", displayPost);
		 data.put("postNum", postNum);
		  
		 return sql.selectList(namespace + ".listpage", data);
	}
	
	}


