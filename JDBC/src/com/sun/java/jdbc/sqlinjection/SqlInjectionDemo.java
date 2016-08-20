package com.sun.java.jdbc.sqlinjection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.java.jdbc.util.DBUtil;

public class SqlInjectionDemo {
	
	public static void main(String[] args) throws SQLException {
		
		//System.out.println(statementSQLInjection("'abc' or 1=1"));
		
		System.out.println(PStatementSQLInjection("'abc' or 1=1"));
	}

	private static boolean statementSQLInjection(String empName) throws SQLException{
		Connection con = null;
		try{
		 con = DBUtil.openConnection();
		 String query = "select * from employee where  emp_name ="+empName;
		 
		 System.out.println(query);
		 Statement st = con.createStatement();
		  ResultSet rs = st.executeQuery(query);
		 if(null != rs && rs.next()){
			 System.out.println(" Emp id : "+rs.getInt("EMP_ID")+"  EMP User Name : "+rs.getString("USER_NAME"));
			 return true;
		 }
			
		}catch(Exception e){
			
		}finally{
			DBUtil.closeConnection(con);
		}
		
		return false;
	}
	
	private static boolean PStatementSQLInjection(String empName) throws SQLException{
		Connection con = null;
		try{
		 con = DBUtil.openConnection();
		String query = "select * from employee where  emp_name = ?";
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1,empName);
		//st.set
		 ResultSet rs = st.executeQuery();
		 if(null != rs && rs.next()){
			 System.out.println(" Emp id : "+rs.getInt("EMP_ID")+"  EMP User Name : "+rs.getString("USER_NAME"));			 
			 return true;
		 }
			
		}catch(Exception e){
			
		}finally{
			DBUtil.closeConnection(con);
		}
		
		return false;
	}
}
