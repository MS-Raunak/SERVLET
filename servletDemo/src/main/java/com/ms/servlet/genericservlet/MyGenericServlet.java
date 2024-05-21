package com.ms.servlet.genericservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/inserdata")
public class MyGenericServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		//Getting data from client side(front-end) after submission
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "tiger");
			 PreparedStatement ps = con.prepareStatement("INSERT INTO STUDENT(ID, NAME, AGE) VALUES(?,?,?)");
			 ps.setInt(1, id);
			 ps.setString(2, name);
			 ps.setInt(3, age);
			 
			 
			 ps.execute();
			 
			 //Print message(Data) on browser
			 res.getWriter().print("<h1>data inserted successfully...</h1>");
			 //Print message on terminal
			 System.out.println("data inserted successfully");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
