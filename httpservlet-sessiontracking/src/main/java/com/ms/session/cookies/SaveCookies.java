package com.ms.session.cookies;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookies are small pieces of data which is created inside the server and stored in the browser storage.
 * Widely used in web development
 * Firstly Server send cookies to the browser as a response but after that client(browser) send the cookies to the server as a request.
 * 
 * Disadvantage:-
 * If user disable permission then we can't store data on the user's browser
 * We can store only simple data as a cookie(many special char we can't store like-whitespace,double quotes,cummma etc)
 * we can't store large amount of data
 */

@WebServlet("/saveCookie")
public class SaveCookies extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Creating Cookie(in server)
		Cookie cookie1 = new Cookie("name", "Chhaya");
		Cookie cookie2 = new Cookie("age", "2");
		
		//Setting expire time of cookies 
		cookie1.setMaxAge(5000); //automatically removed after 5second
		cookie2.setMaxAge(5000); // //automatically removed after 5second
		
		//Saving Cookies in the client(browser) storage: sending by server as a response to the client
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);
		
		//printing message
		resp.getWriter().print("<h1>Cookies Saved Successfully</h1>");
	}

}
