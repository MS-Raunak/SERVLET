package com.ms.servlet.genericservlet.reqDispatcher;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/myGenSevelet2")
public class MyGenServlet2 extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		System.out.println("This is second servlet");
		res.getWriter().print("Hi " + name + ", Welcome to the another servlet");
	}

}
