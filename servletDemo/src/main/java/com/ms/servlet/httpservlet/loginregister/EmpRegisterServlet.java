package com.ms.servlet.httpservlet.loginregister;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class EmpRegisterServlet extends HttpServlet{
	String driver_url = "com.mysql.cj.jdbc.Driver";
	String db_url = "jdbc:mysql://localhost:3306/servlet";
	String db_user = "root";
	String db_password = "tiger";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//input field by user
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		
		
		try {
			Class.forName(driver_url);
			Connection con = DriverManager.getConnection(db_url, db_user, db_password);
			PreparedStatement ps = con.prepareStatement("INSERT INTO EMP(ID,NAME,EMAIL,PASSWORD)VALUES(?,?,?,?)");
			
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			
			ps.execute();
			
			res.getWriter().print("<h1 style='color:green'>Employee Registered Successfully!!</h1>");
			RequestDispatcher rd = req.getRequestDispatcher("loginEmp.html");
			rd.include(req, res);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

}
