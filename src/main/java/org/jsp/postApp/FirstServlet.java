package org.jsp.postApp;

import java.io.*;

import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
public class FirstServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Fetch Ui/form Data
		String sid = req.getParameter("i");
		int id = Integer.parseInt(sid);

		String name = req.getParameter("nm");
		String dept = req.getParameter("dept");
		String sperc = req.getParameter("perce");
		double perc = Double.parseDouble(sperc);

		PrintWriter out = resp.getWriter();
		out.println("<html><body bgcolor='orange'>" + "<h1>Student name is " + name + " from " + dept + " with " + perc + " perc</h1>"
				+ "</body></html>");
		out.close();
		// presentation logic//JDBC -technology

		Connection con = null;
		PreparedStatement pstmt = null;
		String qry = "insert into btm.student3 values(?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=rahul");
			pstmt = con.prepareStatement(qry);

			// set the data for place holder
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, dept);
			pstmt.setDouble(4, perc);

			// execute the SQL Query
			pstmt.executeUpdate();
			System.out.println("record inserted!!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}
}
