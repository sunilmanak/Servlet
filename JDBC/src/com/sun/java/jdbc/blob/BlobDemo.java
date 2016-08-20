package com.sun.java.jdbc.blob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.java.jdbc.util.DBUtil;

public class BlobDemo {
	
	public static void updateResume(int candidateId, String filePath) throws SQLException {
		Connection con = null;
		try{
			con = DBUtil.openConnection();
			String updateSQL = "UPDATE candidates SET resume = ? WHERE id=?";
	 
	         PreparedStatement pstmt = con.prepareStatement(updateSQL);
	         // read the file
	         File file = new File(filePath);
	         FileInputStream input = new FileInputStream(file);
	          
	         // set parameters
	         pstmt.setBinaryStream(1, input);
	         pstmt.setInt(2, candidateId);
	        int count = pstmt.executeUpdate();
	        
	        if(count > 0){
	        	System.out.println("Resume Updated Done SuccessFully");
	        }else{
	        	System.out.println("Resume Not Updated Properly");
	        }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
	}
	
	private static void getBlobFromDB(int candidateId, String filePath){
		Connection con = null;
		String updateSQL = "Select resume from  candidates  WHERE id=?";
		try{
			con = DBUtil.openConnection();
			 PreparedStatement pstmt = con.prepareStatement(updateSQL);
			 pstmt.setInt(1, candidateId);
	         // read the file
	         //File file = new File(filePath);
	         PrintWriter output = new PrintWriter(new FileWriter(filePath));
	         
	         ResultSet rs= pstmt.executeQuery();
	         rs.next();
	         Blob blob = rs.getBlob("resume");
	         BufferedReader br = null;
	         if (blob != null) {
	        	 br = new BufferedReader(new InputStreamReader(blob.getBinaryStream()));
	            }
	         String str = null;
	         while((str = br.readLine())!= null){
	        	 output.write(str);
	         }
	         output.close();
	         br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		//updateResume(444, "C:\\Users\\Sunny\\Desktop\\1.png");
		getBlobFromDB(444, "D:\\JDBC\\blobImage.png");
	}

}
