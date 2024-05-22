package com.ms.servlet.genericservlet.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/postdata")
public class SaveDataServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// Getting data from user or client
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "tiger");
			PreparedStatement ps = con.prepareStatement("INSERT INTO STUDENT(ID,NAME,AGE) VALUES(?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);

			ps.execute();
			res.getWriter().println("<h1>data inserted successfully...insert more data</h1>");

			RequestDispatcher dispatcher = req.getRequestDispatcher("postData.html");

			dispatcher.include(req, res);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
