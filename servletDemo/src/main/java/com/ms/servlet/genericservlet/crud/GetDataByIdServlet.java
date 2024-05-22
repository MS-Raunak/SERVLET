package com.ms.servlet.genericservlet.crud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@WebServlet("/getDataById")
public class GetDataByIdServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// user input id
		int id = Integer.parseInt(req.getParameter("id"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "tiger");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM STUDENT WHERE ID=?");

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				RequestDispatcher rd = req.getRequestDispatcher("getSingleDataById.html");
				res.getWriter().print("<h1 style='color:red'>Invalid ID</h1>");
				rd.include(req, res);
			} else {
				ResultSet rs1 = ps.executeQuery();
				while (rs1.next()) {
					res.getWriter().println("<p>ID: " + rs1.getInt(1) + " Name: " + rs1.getString(2) + " Age: "
							+ rs1.getInt(3) + "</p>");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
