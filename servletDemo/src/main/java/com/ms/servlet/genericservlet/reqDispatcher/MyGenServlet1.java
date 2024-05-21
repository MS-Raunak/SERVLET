package com.ms.servlet.genericservlet.reqDispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Request Dispatcher means sending data from one resource to another
 * we can send data in following resources-
 * 1->servlet -> another servlet
 * 2->servlet -> view resources(html,jsp etc)
 */

@WebServlet("/forwardData")
public class MyGenServlet1 extends GenericServlet{
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException{
		String name = req.getParameter("name");
		
		/*########### Sending Data to the ViewPage ##############*/
		RequestDispatcher rd = req.getRequestDispatcher("forwardedData.html");
		
		//Printwriter allows to display data on the browser rather than terminal
		PrintWriter writer = res.getWriter();
		writer.print("<h1>Hello " + name + "</h1>");
		//forwarding the control only
		//rd.forward(req, res);
		
		//forwarding the control along with data
		rd.include(req, res);
		
		/*########### Sending Data to the another servlet ##############*/
		/*
		RequestDispatcher dispatcher = req.getRequestDispatcher("myGenSevelet2");
		res.getWriter().print(name);
		dispatcher.include(req, res);
		*/
	}

}
