package com.sun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HeaderServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		 
		out.println("<html>");	
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">");
		out.println(" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		out.println("<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>");
		out.println("<title>Sunny Store</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");	
		out.println(" <div class=\"jumbotron\">");
		out.println("<h3>Sunny Store </h3> ");
		out.println("<h5>Welcome to Online Shop </h5> ");
		out.println("<a href=\"./cart\" style=\"float:right;\">Show my cart </a> ");
		out.println(" </div>");
		out.println("</div>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
