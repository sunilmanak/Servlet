package com.sun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.servlet.service.ProductService;
import com.sun.servlet.util.DBUtil;

public class HomeServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		RequestDispatcher rd = req.getRequestDispatcher("/head");
				rd.include(req, resp);
				
		PrintWriter out = resp.getWriter();
		out.println("<div class=\"container\">");
		out.println(" <h2>products </h2>");
		out.println("<table class=\"table\">");
		out.println("<thead> <tr>");
		out.println("<th>Name</th>");
		out.println("<th>Price</th>");
		out.println("<th>Image</th>");
		out.println(" </tr>");
		out.println(" </thead>");
		out.println("<tbody>");
         ProductService prdService = new ProductService();
		
		List<com.sun.servlet.bean.Product> products = prdService.loadAllProducts();
		for(com.sun.servlet.bean.Product product:products){
			String name = product.getName();
			String price = product.getPrice();
			String imgurl = product.getImgUrl();		
		out.println("<tr>");
		out.println("<form action=\"./home\" method=\"post\">");	
		out.println("<td>"+name+"</td>");
		out.println("<input type=\"hidden\" name=\"id\" value=\""+product.getId()+"\">");
		out.println("<td>"+price+"</td>");
		out.println("<td><img src=\""+imgurl+"\" alt=\""+name+"\"></td>");
		out.println("<td><input type=\"submit\" value=\"add to cart\"></td>");
		out.println("</form>");
		out.println("</tr>");
		
		}
		out.println("</tbody>");
		out.println(" </table>");
		out.println(" </div>");
		
		rd = req.getRequestDispatcher("/footer");
		rd.include(req, resp);
		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println("Id :::::::::::: "+id);
		HttpSession session = req.getSession();
		
		List<Integer> productList = (List<Integer>) session.getAttribute("savedProducts");
		if(null == productList){
			productList = new ArrayList<Integer>();
		}
		productList.add(Integer.parseInt(id));
		session.setAttribute("savedProducts",productList);
		
		this.doGet(req, resp);
		
	}
}
