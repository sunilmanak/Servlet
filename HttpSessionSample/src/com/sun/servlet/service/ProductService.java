package com.sun.servlet.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.servlet.bean.Product;
import com.sun.servlet.util.DBUtil;

public class ProductService {

	
	public List<Product> loadAllProducts(){
		Connection con = null;
		List<Product> products = new ArrayList<Product>();
		try {
		 con = DBUtil.openConnection();
		String query =  "select * from products";
		ResultSet rs = con.createStatement().executeQuery(query);
		
		while(rs.next()){
			 Product product = new Product();
			String name = rs.getString("name");
			String price = rs.getString("price");
			String imgurl = rs.getString("image_url");
			int id = rs.getInt("id");			
			 product.setName(name);
			 product.setImgUrl(imgurl);
			 product.setPrice(price);
			 product.setId(id);
			 products.add(product);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return products;
	}
	 		 
	
	public List<Product> loadProductsById(List<Integer> ids){
		Connection con = null;
		List<Product> products = new ArrayList<Product>();
		try {
		 con = DBUtil.openConnection();
		StringBuilder query = new StringBuilder("select * from products where id in (");;
		int i =1;
		for(int id:ids){
			
			if(i == ids.size())
			  query.append(id);
			else
			  query.append(id+",");
			i++;
		}
		
		query.append(")");
		System.out.println("Query : "+query);
		ResultSet rs = con.createStatement().executeQuery(query.toString());
		
		while(rs.next()){
			String name = rs.getString("name");
			String price = rs.getString("price");
			String imgurl = rs.getString("image_url");
			int id = rs.getInt("id");
			 Product product = new Product();
			 product.setName(name);
			 product.setImgUrl(imgurl);
			 product.setPrice(price);
			 product.setId(id);
			 products.add(product);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return products;
	}
}
