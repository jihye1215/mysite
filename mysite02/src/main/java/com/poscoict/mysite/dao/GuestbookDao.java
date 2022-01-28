package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.GuestbookVo;

public class GuestbookDao {
   public List<GuestbookVo> selectAll() {
      List<GuestbookVo> result = new ArrayList();
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         
         conn = getConnection();
         
         String sql = "select no, name, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, message from guestbook order by reg_date desc";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            Long no = rs.getLong(1);
            String name = rs.getString(2);
            String reg_date = rs.getString(3);
            String message = rs.getString(4);
            
            GuestbookVo vo = new GuestbookVo();
            vo.setNo(no);
            vo.setName(name);
            vo.setReg_date(reg_date);
            vo.setMessage(message);
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
   
   public boolean insert(GuestbookVo vo) {
      boolean result = false;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = getConnection();
         
         String sql = "insert into guestbook values(null, ?, ?, ?, now())";
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1,  vo.getName());
         pstmt.setString(2, vo.getPassword());
         pstmt.setString(3, vo.getMessage());
         
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
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mysql://192.168.0.78:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch(ClassNotFoundException e) {
			System.out.print("드라이버 로딩 실패 : "+e);
		}
		return conn;
	}

}