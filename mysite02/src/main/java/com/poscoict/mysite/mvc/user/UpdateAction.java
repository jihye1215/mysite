package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인을 해야만 실행될 수 있다. -> 접근제어
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {   // 접근제어
			MvcUtil.redirect(request.getContextPath() + "/user?a = loginform", request, response);
			return;
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String nostring = request.getParameter("no");
		Long no = Long.parseLong(nostring);
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setGender(gender);
		if(password.isBlank() == false) {
			vo.setPassword(password);
			vo.setNo(no);
			
			new UserDao().update(vo);
		} else {
			vo.setNo(no);
			
			new UserDao().updateNopassword(vo);
		}
		session.setAttribute("authUser", vo);
		
		
		MvcUtil.forward("main/index", request, response);
		

	}

}