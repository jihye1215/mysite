package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(nostring);
		
		BoardVo vo = new BoardVo();
		vo = new BoardDao().rOne(no);
		request.setAttribute("selectvo", vo);
		
		MvcUtil.forward("board/modify", request, response);
	}

}
