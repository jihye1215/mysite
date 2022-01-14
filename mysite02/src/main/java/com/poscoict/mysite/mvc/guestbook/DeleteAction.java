package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.GuestbookDao;
import com.poscoict.mysite.web.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(nostring);
		String password = request.getParameter("password");

		GuestbookDao dao = new GuestbookDao();
		dao.delete(no, password);
		
		response.sendRedirect(request.getContextPath() + "/guestbook");

	}

}
