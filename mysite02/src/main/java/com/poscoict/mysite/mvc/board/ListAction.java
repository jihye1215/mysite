package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("kwd");
		BoardDao dao = new BoardDao();
		List<BoardVo> blist = null;
		
		if(keyword == null) {
			blist = dao.selectAll();
		} else {
			blist = dao.Keyselect(keyword);
		}
		
		request.setAttribute("blist", blist);
		MvcUtil.forward("board/list", request, response);
	}

}
