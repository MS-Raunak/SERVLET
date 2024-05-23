package com.ms.session.cookies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Note- To fetching cookie from client storage first we need to store cookie otherwise will get exception
 */

@WebServlet("/getCookie")
public class GetCookies extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cookie[]cookies =  req.getCookies();
		
		for(Cookie cookie : cookies) {
			res.getWriter().println("<h1>" + cookie.getName() + ": " + cookie.getValue() + "</h1>");
		}
	}
}
