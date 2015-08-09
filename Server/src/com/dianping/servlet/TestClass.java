package com.dianping.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestClass extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String age = req.getParameter( "age" );
		resp.setContentType( "text/html" );
		PrintWriter out = resp.getWriter();
		out.println( "<html>" );
		out.println( "<body>" ) ;
		out.println( "<h1>Hello Get</h1>" );
		out.println( "<h1>Age : " + age + "</h1>"  );
		out.println( "</body>" );
		out.println( "</html>" ); 
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
