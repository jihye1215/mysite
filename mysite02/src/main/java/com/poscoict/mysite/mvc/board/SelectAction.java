package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class SelectAction implements Action {
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(nostring);
		
		BoardVo vo = new BoardVo();
		vo = new BoardDao().rOne(no);
		request.setAttribute("selectvo", vo);
		
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(nostring.equals(cookie.getName())) {
					viewCookie = cookie;
					break;
				} 
						
			}
		}
		
		// 쿠키 쓰기(굽기)
		if(viewCookie == null) {
			 Cookie cookie = new Cookie(nostring, String.valueOf((vo.getHit() + 1)));
	           cookie.setPath(request.getContextPath());
	           cookie.setMaxAge(60);    
	           response.addCookie(cookie);
	           new BoardDao().views(vo);

		} else {

		}
		
		 MvcUtil.forward("board/view", request, response);
	}

}
