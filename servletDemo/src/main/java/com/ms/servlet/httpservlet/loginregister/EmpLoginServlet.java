package com.ms.servlet.httpservlet.loginregister;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class EmpLoginServlet extends HttpServlet {
	String driver_url = "com.mysql.cj.jdbc.Driver";
	String db_url = "jdbc:mysql://localhost:3306/servlet";
	String db_user = "root";
	String db_password = "tiger";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// input field by user
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			Class.forName(driver_url);
			Connection con = DriverManager.getConnection(db_url, db_user, db_password);
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EMP WHERE EMAIL=? AND PASSWORD=?");

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				res.getWriter().print("<h1 style='color:green'>Logged-in Successfully!!</h1>");
				Cookie ck = new Cookie("email", email);
				Cookie ck1 = new Cookie("password", password);

				res.addCookie(ck);
				res.addCookie(ck1);

			}
			else {
				res.getWriter().print("<h1 style='color:red'>Invalid Credentials!</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("loginEmp.html");
				rd.include(req, res);
			}

			/*
			 * Login By Cookies are having some error
			 * Cookie ck = new Cookie(email, password);
			Cookie[] cookies = req.getCookies();
			if (cookies == null) {
				res.getWriter().print("<h1 style='color:red'>Invalid Credentials!</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("loginEmp.html");
				rd.include(req, res);
			} 
			else {
				res.getWriter().print("<h1 style='color:green'>Logged-in Successfully!!</h1>");
			}
			*/

		} catch (ClassNotFoundException |

				SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}
}
