package com.cetpa;

import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.DriverManager;

@WebServlet("/save-record")
public class SaveRecordServlet extends HttpServlet
{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/jsp5","root","mysql");
			String sql="insert into customerinfo values(?,?,?,?)";
			PreparedStatement ps=cn.prepareStatement(sql);
			// get value from request object
			ps.setString(1, request.getParameter("cid"));
			ps.setString(2, request.getParameter("name"));
			ps.setString(3, request.getParameter("phone"));
			ps.setString(4, request.getParameter("address"));
			ps.executeUpdate();
			
			pw.println("<html> <body>");
			
			pw.println("<h2> Customer record has been saved successfully </h2>");
			
			pw.println("<a href='insert.html' style='font-size:22px'> Add more record </a>");
			pw.println("<a href='/customerinfo' style='font-size:22px'>Home</a>");
			
			pw.println("</body> </html>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}
