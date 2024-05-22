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

@WebServlet("/updateAge")
public class UpdateAgeByIdServlet extends GenericServlet{
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// user input id
		int id = Integer.parseInt(req.getParameter("id"));
		int age = Integer.parseInt(req.getParameter("age"));

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "tiger");

			PreparedStatement pstatement = con.prepareStatement("SELECT * FROM STUDENT WHERE ID=?");
			pstatement.setInt(1, id);

			ResultSet rset = pstatement.executeQuery();

			if (!rset.next()) {
				res.getWriter().print("<h1 style='color:red'>Invalid Id</h1>");
				RequestDispatcher dispatcher = req.getRequestDispatcher("updateDataName.html");
				dispatcher.include(req, res);
			}

			else {
				PreparedStatement ps = con.prepareStatement("UPDATE STUDENT SET Age=? WHERE ID=?");
				ps.setInt(1, age);
				ps.setInt(2, id);
				
				ps.execute();

				res.getWriter().print("<h1 style='color:green'>Data Updated Successfully</h1>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
