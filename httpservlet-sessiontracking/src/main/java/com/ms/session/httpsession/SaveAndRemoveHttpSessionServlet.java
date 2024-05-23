package com.ms.session.httpsession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/saveHttpSession")
public class SaveAndRemoveHttpSessionServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Create Session
		HttpSession session = req.getSession();
		
		//Set Session Value
		session.setAttribute("id", 1);
		session.setAttribute("name", "Chhaya");
		session.setAttribute("age", 2);
		
		//Get Session 
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		Integer age = (Integer) session.getAttribute("age");
		
		resp.getWriter().println("id:" + id + " name: " + name + " age: " + age);
		
		//Remove a particular session's attribute
		session.removeAttribute("age");
		Integer age1 = (Integer) session.getAttribute("age");
		resp.getWriter().println("id:" + id + " name: " + name + " age: " + age1);//age become null
		
		//Remove session
		session.invalidate();
		
	}
}
