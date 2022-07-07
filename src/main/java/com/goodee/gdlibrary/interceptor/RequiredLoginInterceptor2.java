package com.goodee.gdlibrary.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class RequiredLoginInterceptor2 implements HandlerInterceptor {

	// 책 대여는 로그인 한 사용자만 할 수 있으므로 미리 점검해야 한다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getSession().getAttribute("loginMember") == null) {
			Long bookNo = Long.parseLong(request.getParameter("bookNo"));
			System.out.println(bookNo);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 할까요?')){");
			out.println("location.href='" + request.getContextPath() + "/member/loginPage?url=" + request.getContextPath() + "/book/detail?bookNo=" + bookNo + "'");
			out.println("}else{");
			out.println("history.back()");
			out.println("}");
			out.println("</script>");
			out.close();
			return false;
		}
		
		return true;
		
	}
}
