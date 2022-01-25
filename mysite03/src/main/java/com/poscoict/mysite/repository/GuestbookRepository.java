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

import com.poscoict.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
   public List<GuestbookVo> selectAll() {
     return sqlSession.selectList("guestbook.selectAll");
   }
   
   public int insert(GuestbookVo vo) {
	  return sqlSession.insert("guestbook.insert", vo);
   }
      
   public boolean delete(Long no, String password) {
	   Map<String, Object> map = new HashMap<>();
	   map.put("no", no);
	   map.put("password", password);
	   return sqlSession.delete("guestbook.delete", map) == 1;
   }
	    
   public int selectCnt(){
	   return sqlSession.selectOne("guestbook.selectCnt");
   }

}