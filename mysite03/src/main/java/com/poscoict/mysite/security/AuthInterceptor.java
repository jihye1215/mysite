package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. 캐스팅(casting) 
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method @Auth가 없다면 Type에 있는지 확인해보는 것이 필요...(과제)
		if(auth == null) {
			// 강사님 코드
			// auth = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		// 5. type과 method에 @Auth가 적용이 안되어 있는 경우 
		if(auth == null) {
			return true;
		}

		// 5. @Auth가 적용이 되어있기 때문에 인증(Authentication)여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 강사님 코드 
		// 1. 권한 체크를 위해서 @Auth의 role 가져오기("USER", "ADMIN")
		String role = auth.role();
		
		// 2. @Auth의 role이 "USER"인 경우, authUser의 role은 상관이 없다.
		if("USER".equals(role)) {
			return true;
		}
		
		// 3. @Auth의 role이 "ADMIN"인 경우, authUser은 "ADMIN"이어야 한다.
		if("ADMIN".equals(authUser.getRole()) == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 4. 옳은 관리자
		// 5. @Auth role : "ADMIN", authUser의 role : "ADMIN"
		return true;
		
		
		// 6. 인증 확인!!! -> Controller의 Handler(method) 실행
//		if(authUser.getRole().equals("ADMIN")) {
//			return true;
//		}
//		if(!auth.role().equals(authUser.getRole())) {
//			response.sendRedirect(request.getContextPath());
//			return false;
//		}
//		return true;
	}

}
