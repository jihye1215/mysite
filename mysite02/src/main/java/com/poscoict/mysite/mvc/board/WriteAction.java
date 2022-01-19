package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(nostring);
		String ordernostring = request.getParameter("orderno");
		String groupnostring = request.getParameter("groupno");
		String depthstring = request.getParameter("depth");

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(no);
		if(ordernostring == null && groupnostring == null) {
			new BoardDao().insertNew(vo);
		} else if (ordernostring != null && groupnostring != null){
			int orderno = Integer.parseInt(ordernostring);
			int groupno = Integer.parseInt(groupnostring);
			int depth = Integer.parseInt(depthstring);
			vo.setOrderno(orderno);
			vo.setGroupno(groupno);
			vo.setDepth(depth);
			new BoardDao().updateReply(orderno, groupno);
			new BoardDao().insertReply(vo);
		}
		
		MvcUtil.redirect(request.getContextPath() + "/board", request, response);
	}

}
