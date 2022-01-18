package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(nostring);
		
		BoardDao dao = new BoardDao();
		dao.delete(no);

		response.sendRedirect(request.getContextPath() + "/board");
	}

}
