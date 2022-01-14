package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class JoinFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// joinform.jsp로 forward 시켜주면 된다.
		MvcUtil.forward("user/joinform", request, response);
		
	}

}
