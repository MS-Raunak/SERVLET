package com.ms.session.httpsession;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sendSession")
public class SessionSendServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("id", 1);
		session.setAttribute("name", "Shanaya");
		
		//Forwarding Session to another servlet
		RequestDispatcher rd = req.getRequestDispatcher("receiveSession");
		rd.forward(req, resp);
	}
	
}
