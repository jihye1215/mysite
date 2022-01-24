package com.poscoict.mysite.repository;

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

public class BoardRepository {
	
	public List<BoardVo> selectAll(int num) {
		// 글 조회하는 기능
	      List<BoardVo> result = new ArrayList();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         conn = getConnection();
	         
	         String sql = "select b.no, b.title, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, b.user_no, b.g_no, b.o_no, b.depth "
	        		 + "from user a, board b "
	        		 + "where a.no = b.user_no "
	        		 + "order by g_no desc, o_no asc "
	        		 + "limit " + (num-1)*5 + ", 5";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            Long no = rs.getLong(1);
	            String title = rs.getString(2);
	            String name = rs.getString(3);
	            int hit = rs.getInt(4);
	            String regDate = rs.getString(5);
	            Long userNo = rs.getLong(6);
	            int groupno = rs.getInt(7);
	            int orderno = rs.getInt(8);
	            int depth = rs.getInt(9);
	            
	            BoardVo vo = new BoardVo();
	            vo.setNo(no);
	            vo.setTitle(title);
	            vo.setUserName(name);
	            vo.setHit(hit);
	            vo.setRegDate(regDate);
	            vo.setUserNo(userNo);
	            vo.setGroupno(groupno);
	            vo.setOrderno(orderno);
	            vo.setDepth(depth);
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
	
	public List<BoardVo> Keyselect(String keyword, int num) {
		// 키워드 글 조회하는 기능
	      List<BoardVo> result = new ArrayList();
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         
	         conn = getConnection();
	         
	         String sql = "select b.no, b.title, a.name, b.hit, date_format(b.reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, b.user_no, b.g_no, b.o_no, b.depth "
	        		 + "from user a, board b "
	        		 + "where a.no = b.user_no "
	        		 + "and title like '%" + keyword + "%'"
	        		 + "order by g_no desc, o_no asc "
	        		 + "limit " + (num-1)*5 + ", 5";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            Long no = rs.getLong(1);
	            String title = rs.getString(2);
	            String name = rs.getString(3);
	            int hit = rs.getInt(4);
	            String regDate = rs.getString(5);
	            Long userNo = rs.getLong(6);
	            int groupno = rs.getInt(7);
	            int orderno = rs.getInt(8);
	            int depth = rs.getInt(9);
	            
	            BoardVo vo = new BoardVo();
	            vo.setNo(no);
	            vo.setTitle(title);
	            vo.setUserName(name);
	            vo.setHit(hit);
	            vo.setRegDate(regDate);
	            vo.setUserNo(userNo);
	            vo.setGroupno(groupno);
	            vo.setOrderno(orderno);
	            vo.setDepth(depth);
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
	         
	         String sql = "insert into board values(null, ?, ?, 0, (select IFNULL(max(g_no), 0)+1 from board a), 1, 1, now(), ?)";
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
	         
	         String sql = "select no, title, contents, user_no, g_no, o_no, depth from board where no = " + no;
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	        	Long no1 = rs.getLong(1);
	            String title = rs.getString(2);
	            String contents = rs.getString(3);
	            Long userNo = rs.getLong(4);
	            int groupno = rs.getInt(5);
	            int orderno = rs.getInt(6);
	            int depth = rs.getInt(7);
	            
	            vo = new BoardVo();
	            vo.setNo(no1);
	            vo.setTitle(title);
	            vo.setContents(contents);
	            vo.setUserNo(userNo);
	            vo.setGroupno(groupno);
	            vo.setOrderno(orderno);
	            vo.setDepth(depth);
	            
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
	
	public boolean update(BoardVo vo) {
		// 글 삭제하는 기능
	      
	      boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = "update board set title = ?, contents = ? where no = ?";
	         pstmt = conn.prepareStatement(sql);

	         //4. 바인딩(binding)   
	         pstmt.setString(1, vo.getTitle());
	         pstmt.setString(2, vo.getContents());
	         pstmt.setLong(3, vo.getNo());
	         
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
	
	public boolean views(BoardVo vo) {
		// 조회수 증가하는 기능
		boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = "update board set hit = hit + 1 where no = ?";
	         pstmt = conn.prepareStatement(sql);

	         //4. 바인딩(binding)   
	         pstmt.setLong(1, vo.getNo());
	         
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
	
	public boolean updateReply(int Orderno, int Groupno) {
		// orderno 증가
		boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         //3. SQL 준비
	         String sql = "update board set o_no = o_no + 1 where o_no > " + Orderno + " and g_no = " + Groupno;
	         pstmt = conn.prepareStatement(sql);

	         //4. 바인딩(binding)   
	         
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
	
	public boolean insertReply(BoardVo vo) {
		// 답글 작성하는 기능
	      boolean result = false;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = getConnection();
	         
	         String sql = "insert into board values(null, ?, ?, 0, ?, ?, ?, now(), ?)";
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1,  vo.getTitle());
	         pstmt.setString(2, vo.getContents());
	         pstmt.setInt(3, vo.getGroupno());
	         pstmt.setInt(4, vo.getOrderno() + 1);
	         pstmt.setInt(5, vo.getDepth() + 1);
	         pstmt.setLong(6, vo.getUserNo());
	         
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
	
	public int cnt() {
		// 페이징 
	      BoardVo vo = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int cnt;
	      
	      try {
	         
	         conn = getConnection();
	         
	         String sql = "select count(*) from board";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         cnt = rs.getInt(1);
	         
	      } catch(SQLException e) {
	    	  cnt = 0;
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
	      return cnt;
	      
	   }
	
	public int cntKey(String keyword) {
		// 페이징 
	      BoardVo vo = null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      int cnt;
	      
	      try {
	         
	         conn = getConnection();
	         
	         String sql = "select count(*) from board where title like '%" + keyword + "%'";
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         cnt = rs.getInt(1);
	         
	      } catch(SQLException e) {
	    	  cnt = 0;
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
	      return cnt;
	      
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
