package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	      
	      boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = "delete from guestbook where no = ? and password = ?";
	         pstmt = conn.prepareStatement(sql);

	         //4. 바인딩(binding)   
	         pstmt.setLong(1, no);
	         pstmt.setString(2, password);
	         
	         //5. SQL 실행 , executeQuery는 rs, executeUpdate는 int로 반환한다. 
	         result = (pstmt.executeUpdate() == 1);
  
	      } catch (SQLException e) {
	         System.out.print("error : " + e); 
	      }
	      
	      finally {
	         try {
	            if(rs != null) {
	               rs.close();
	            }
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(conn != null) {
	               conn.close();
	            }
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      return result;
	   }
	   
   public int selectCnt(){
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int result = 0;
	      
	      try {
	         conn = getConnection();

	         String sql = "select count(*) from guestbook";
	         pstmt = conn.prepareStatement(sql);

	         rs = pstmt.executeQuery();

	         if(rs.next()) {
	            result = rs.getInt(1);
	         }
	         
	      } catch (SQLException e) {
	         System.out.print("error : " + e); 
	      }
	      
	      finally {
	         try {
	            if(rs != null) {
	               rs.close();
	            }
	            if(pstmt != null) {
	               pstmt.close();
	            }
	            if(conn != null) {
	               conn.close();
	            }
	         } catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
	   }

   
   private Connection getConnection() throws SQLException {
      Connection conn = null;
      try {
         // 1. JDBC 드라이버 로딩
         Class.forName("com.mysql.cj.jdbc.Driver");
         // 2. 연결하기
         String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
         conn = DriverManager.getConnection(url, "webdb", "webdb");
      } catch(ClassNotFoundException e) {
         System.out.print("드라이버 로딩 실패 : "+e);
      }
      return conn;
   }

}