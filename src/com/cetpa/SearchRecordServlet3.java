package com.cetpa;

import javax.servlet.*;


import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.DriverManager;

@WebServlet("/search-record")
public class SearchRecordServlet3 extends HttpServlet
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
			String sql="select * from customerinfo where cid=?";
			PreparedStatement ps=cn.prepareStatement(sql);
			// get value from request object
			String v1=request.getParameter("cid");
			ps.setString(1, v1);
			
			//int n=ps.executeUpdate();
			
			ResultSet rst=ps.executeQuery();
			pw.println("<html> <body>");
			if(rst.next())
			{
				pw.println("<table border='1' style='border-collapse:collapse; font-size:20px; width:40%' cellpadding='4px'> ");
				
				pw.println("<tr style='background-color:orange;color:white'><th colspan='2' align='center'>Customer Details</th></tr>");
				pw.println("<tr><th align='left'>Customer id</th><td>"+rst.getString(1)+"</td></tr>");
				pw.println("<tr><th align='left'>Customer name</th><td>"+rst.getString(2)+"</td></tr>");
				pw.println("<tr><th align='left'>Customer phone</th><td>"+rst.getString(3)+"</td></tr>");
				pw.println("<tr><th align='left'>Customer address</th><td>"+rst.getString(4)+"</td></tr>");
				pw.println("</table>");
				
			}
			else
			{
				pw.println("<h2 style='color:red'> Customer record has been not found</h2>");
			}
			
			
			pw.println("<br></br><a href='search.html' style='font-size:22px'> Search more record </a>");
			pw.println("<a href='/customerinfo' style='font-size:22px'>Home</a>");
			
			
			
			pw.println("</body> </html>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}
