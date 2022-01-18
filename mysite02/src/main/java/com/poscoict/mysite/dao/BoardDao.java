package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.vo.GuestbookVo;

public class BoardDao {
	
	public List<BoardVo> selectAll() {
		// 글 조회하는 기능
	      List<BoardVo> result = new ArrayList();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         conn = getConnection();
	         
	         String sql = "select b.no, b.title, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date "
	        		 + "from user a, board b "
	        		 + "where a.no = b.user_no "
	        		 + "order by reg_date desc ";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            Long no = rs.getLong(1);
	            String title = rs.getString(2);
	            String name = rs.getString(3);
	            int hit = rs.getInt(4);
	            String regDate = rs.getString(5);
	            
	            
	            BoardVo vo = new BoardVo();
	            vo.setNo(no);
	            vo.setTitle(title);
	            vo.setUserName(name);
	            vo.setHit(hit);
	            vo.setRegDate(regDate);
	            result.add(vo);
	         }
	      } catch(SQLException e) {
	         System.out.println("error : " + e);
	      } finally {
	         // 자원 정리
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
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
	      
	   }
	
	public boolean insertNew(BoardVo vo) {
		// 새 글 작성하는 기능
	      boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         String sql = "insert into board values(null, ?, ?, 0, 1, 1, 1, now(), ?)";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1,  vo.getTitle());
	         pstmt.setString(2, vo.getContents());
	         pstmt.setLong(3, vo.getUserNo());
	         
	         result = (pstmt.executeUpdate() == 1);
	      } catch(SQLException e) {
	         System.out.print("error : "+e);
	      }finally {
	         // 자원 정리
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
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return result;
	   }
	
	public boolean delete(Long no) {
		// 글 삭제하는 기능
	      
	      boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = "delete from board where no = ?";
	         pstmt = conn.prepareStatement(sql);

	         //4. 바인딩(binding)   
	         pstmt.setLong(1, no);
	         
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
	
	public BoardVo rOne(Long no) {
		// 글 조회하는 기능
	      BoardVo vo = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         conn = getConnection();
	         
	         String sql = "select title, contents from board where no = " + no;
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            String title = rs.getString(1);
	            String contents = rs.getString(2);
	            
	            vo = new BoardVo();
	            vo.setTitle(title);
	            vo.setContents(contents);
	         }
	      } catch(SQLException e) {
	         System.out.println("error : " + e);
	      } finally {
	         // 자원 정리
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
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return vo;
	      
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
