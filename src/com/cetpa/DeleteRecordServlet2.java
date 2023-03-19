package com.cetpa;

import javax.servlet.*;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.DriverManager;

@WebServlet("/delete-record")
public class DeleteRecordServlet2 extends HttpServlet
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
			String sql="delete from customerinfo where cid=?";
			PreparedStatement ps=cn.prepareStatement(sql);
			// get value from request object
			String v1=request.getParameter("cid");
			ps.setString(1, v1);
			
			int n=ps.executeUpdate();
			
			pw.println("<html> <body>");
			if(n>=1)
			{
				pw.println("<h2>Customer record with id "+v1+" Customer record has been deleted successfully </h2>");
			}
			else
			{
				pw.println("<h2 style='color:red'> Customer record has been not found</h2>");
			}
			
			
			pw.println("<a href='delete.html' style='font-size:22px'> Delete more record </a>");
			pw.println("<a href='/customerinfo' style='font-size:22px'>Home</a>");
			
			pw.println("</body> </html>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	
}
