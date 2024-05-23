package com.ms.session.httpsession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/receiveSession")
public class SessionReceive extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Receiving Session that is sending by SessionSendServlet
		HttpSession hs = req.getSession();
		Integer id = (Integer) hs.getAttribute("id");
		String name = (String) hs.getAttribute("name");
		
		resp.getWriter().print("id: " + id + " name: " + name);
	}
}
