package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> selectAll(int num) {
		// 글 조회하는 기능
		return sqlSession.selectList("board.selectAll", (num-1)*5);
	}
	
	public List<BoardVo> Keyselect(String keyword, Integer num) {
		Map<String, Object> map = new HashMap<>();
		map.put("num", (num-1)*5);
		map.put("keyword", keyword);
		return sqlSession.selectList("board.Keyselect", map);
	}
		
	public boolean insertNew(BoardVo vo) {
		int count = sqlSession.insert("board.insertNew", vo);
		return count == 1;
	}
		
	
	public boolean delete(Long no) {
		// 글 삭제하는 기능
		int count = sqlSession.delete("board.delete", no);
		return count == 1;
	}
		
	public BoardVo rOne(Long no) {
		// 글 조회하는 기능
		return sqlSession.selectOne("board.rOne", no);
	}
		
	public boolean update(BoardVo vo) {
		// 글 수정하는 기능
		int count = sqlSession.update("board.update", vo);
		return count == 1;
	}
	
	public boolean views(Long no) {
		int count = sqlSession.update("board.views", no);
		return count == 1;
	}
		
	public boolean updateReply(Integer Orderno, Integer Groupno) {
		Map<Object, Object> map = new HashMap<>();
		map.put("Orderno", Orderno);
		map.put("Groupno", Groupno);
		return sqlSession.update("board.updateReply", map) == 1;
	}
	
	public boolean insertReply(BoardVo vo) {
		// 답글 작성하는 기능
		Map<Object, Object> map = new HashMap<>();
		map.put("Orderno", vo.getOrderno() + 1);
		map.put("depth", vo.getDepth() + 1);
		map.put("vo", vo);
		int count = sqlSession.insert("board.insertReply", map);
		return count == 1;
	}
		
	public int cnt() {
		return sqlSession.selectOne("board.cnt");
	}
	
	public int cntKey(String keyword) {
		// return sqlSession.selectOne("board.getTotalCount", "%" + keyword + "%");
		 return sqlSession.selectOne("board.cntKey", keyword);
	}

}
