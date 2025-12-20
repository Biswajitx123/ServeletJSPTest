package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginForm")
public class login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//super.doPost(req, resp);
		String myEmail = req.getParameter("email2");
		String myPass = req.getParameter("pass2");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##bapu","123456");
			
			PreparedStatement ps = con.prepareStatement("select * from register where email=? and password=?");
			ps.setString(1, myEmail);
			ps.setString(2, myPass);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.include(req, resp);
			}
			else
			{
				out.print("<h3 style='color:red'>Invalid email or password</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.include(req, resp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			out.print("<h3 style='color:red'>Invalid email or password</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
		
		
	}

}
