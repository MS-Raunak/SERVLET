package com.ms.servlet.genericservlet.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getAllData")
public class GetAllDataServlet extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "tiger");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENT");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res.getWriter().println("<p>ID: " + rs.getInt(1) + " Name: " + rs.getString(2) + " Age: " + rs.getInt(3)+"</p>");
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
