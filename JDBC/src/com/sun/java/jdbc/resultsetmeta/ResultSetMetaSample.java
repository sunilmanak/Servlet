package com.sun.java.jdbc.resultsetmeta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaSample {

	public static void main(String[] args) throws Exception {
		
		Connection con  = openConnection();
		Statement st = con.createStatement();
		String query =  "select * from products";
		boolean queryFalg = st.execute(query);
		if(queryFalg){
		  ResultSet rs = st.getResultSet();
		     ResultSetMetaData rsMeta = rs.getMetaData();
		    // rsMeta.
		     
		     System.out.println(rsMeta.getTableName(1));
		     
		     System.out.println("Column Count : "+rsMeta.getColumnCount());
		     
		     System.out.println();
		     
		     for(int i=1;i<=rsMeta.getColumnCount();i++){
		    	 System.out.println(rsMeta.getColumnName(i));
		     }
		     
		     System.out.println();
		    
		    int roucount = 0 ;
		    	while(rs.next()){
		    		for(int i =1;i<=rsMeta.getColumnCount();i++){
				    	System.out.println(rsMeta.getColumnName(i) +"   "+rs.getString(rsMeta.getColumnName(i)));
		    		}		    		
		    		System.out.println("------------------------------------------------------");
		    		roucount++;
		   }
		    	
		    	//System.out.println("Number of rows fetched the result set : "+roucount);

			
		}
		else{
			System.err.println("Query not executed successfully");
		}
		
		
	}
	
	
	
	
	/**
	 * open connection
	 * @return
	 * @throws SQLException
	 */
	public static Connection openConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return  DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","sunilm");		
	}
	
	/**
	 * closes connection
	 * @param con
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws SQLException{
		if(null != con){
			con.close();
		}
	}
	
	
}
