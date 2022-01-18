package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int pageCount = 10;
		int currentPage = 2;
		int nextPage = -1;
		int startPage = 3;
		int prepage = 2;
		// Map에 put해서 하면 됨
		
		MvcUtil.forward("board/list", request, response);
	}

}
