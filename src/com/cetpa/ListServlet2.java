package com.cetpa;

import javax.servlet.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.DriverManager;

@WebServlet("/customer-list")
public class ListServlet2 extends HttpServlet
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
			String sql="select *  from customerinfo";
			Statement st=cn.createStatement();
			ResultSet rst=st.executeQuery(sql);
			// get value from request object
			
			
			pw.println("<html> <body>");
			
			//create table for show list
			pw.println("<table border='1' style='border-collapse:collapse; font-size:20px; width:50%' cellpadding='4px'> ");
			
			pw.println("<tr style='background-color:orange;color:white'><th colspan='4' align='center'>Customer List</th></tr>");
			
			pw.println("<tr><th> Customer id </th><th> Customer name </th><th> Customer phone </th><th> Customer address </th></tr>");
			
			while(rst.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rst.getString(1)+"</td>");
				pw.println("<td>"+rst.getString(2)+"</td>");
				pw.println("<td>"+rst.getString(3)+"</td>");
				pw.println("<td>"+rst.getString(4)+"</td>");				
				pw.println("<tr>");
			}
			
			pw.println("</table>");
			
			
			
			
			pw.println("<br></br><a href='/customerinfo' style='font-size:22px'>Home</a>");
			
			pw.println("</body> </html>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}
