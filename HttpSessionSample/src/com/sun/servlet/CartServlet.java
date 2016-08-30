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

import com.sun.servlet.service.ProductService;

public class CartServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		List<Integer> productList = (List<Integer>) session.getAttribute("savedProducts");
		PrintWriter out = res.getWriter();
		 
		out.println("<html>");	
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">");
		out.println(" <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>");
		out.println("<script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println(" <div class=\"jumbotron\">");
		if(null != productList){
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
			
			List<com.sun.servlet.bean.Product> products = prdService.loadProductsById(productList);
			for(com.sun.servlet.bean.Product product:products){
				String name = product.getName();
				String price = product.getPrice();
				String imgurl = product.getImgUrl();
			out.println("<form action=\"./home\" method=\"post\">");	
			out.println("<tr>");
			out.println("<td>"+name+"</td>");
			out.println("<td><input type=\"hidden\" name=\"id\" value=\""+product.getId()+"\"></td>");
			out.println("<td>"+price+"</td>");
			out.println("<td><img src=\""+imgurl+"\"></td>");
			out.println("<td><input type=\"submit\" value=\"Buy\"></td>");
			out.println("</tr>");
			out.println("</form>");
			}
			out.println("</tbody>");
			out.println(" </table>");
			out.println(" </div>");
			
			
		}else{
			out.println("<h3>No Products to display in your cart </h3> ");
		}
		out.println(" </div>");
		out.println("</div>");
	}
}
