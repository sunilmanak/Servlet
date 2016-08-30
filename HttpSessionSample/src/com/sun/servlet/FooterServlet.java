package com.sun.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FooterServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			PrintWriter out = res.getWriter();
			 
			
			
			out.println("<div class=\"container\">");
			out.println(" <div class=\"jumbotron\">");
			out.println("<p>&copy; 2016 sunnystore.com<p> ");
			out.println(" </div>");
			out.println("</div>");
			out.println("</html>");
			
			
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			this.doGet(req, resp);
		}
	
}
