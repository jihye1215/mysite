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
		String pagenumstring = request.getParameter("pagenum");
		int pagenum = 1;
		if(pagenumstring != null) {
			pagenum = Integer.parseInt(pagenumstring);
		}
		
		BoardDao dao = new BoardDao();
		List<BoardVo> blist = null;
		
		int cnt = dao.cnt();
		int newcnt = 0;
		if(cnt%5 == 0) {
			newcnt = cnt/5;
		} else if(cnt%5 != 0) {
			newcnt = cnt/5 + 1;
		}
		request.setAttribute("newcnt", newcnt);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pagenum", pagenum);
		
		
		if(keyword == null) {
			blist = dao.selectAll(pagenum);
		} else {
			blist = dao.Keyselect(keyword, pagenum);
		}
		
		request.setAttribute("blist", blist);
		MvcUtil.forward("board/list", request, response);
	}

}
