package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.dbconn.DbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regForm")
public class register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String myname = req.getParameter("name");
		String myemail = req.getParameter("email");
		String mypass = req.getParameter("pass");
		String mycity = req.getParameter("city");
		String mystate = req.getParameter("state");
		
		try
		{
			Connection con = new DbConnection().getConnection();
			
			String insert_sql_query = "INSERT INTO REGISTER VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql_query);
			ps.setString(1, myname);
			ps.setString(2, myemail);
			ps.setString(3, mypass);
			ps.setString(4, mycity);
			ps.setString(5, mystate);
			
			int count = ps.executeUpdate();
			if(count > 0)
			{
				out.println("<h3 style='color:green'>Registered Successfully</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp); 
			}
			else
			{
				out.println("<h3 style='color:green'>User not Registered due to some error</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
