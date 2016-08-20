package com.sun.java.jdbc.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BatchPreparedStatementSample {

	
	public static void main(String[] args) throws Exception {
		 long startTime = System.currentTimeMillis();
		System.out.println("Inside BatchPreparedStatementSample main() Starts");
		List<String> dataList =  loadFile();
		 Connection con = openConnection();		
		 String qry = "insert into account (id, name, balance, type)  values (?,?,?,?)";
		 PreparedStatement pst = con.prepareStatement(qry);
		 int startId = getMaxAccountId(con);
		 for(String line : dataList){
			 startId++;
			 String[] accountDetails = line.split("\\|");
			 if(null != accountDetails && accountDetails.length > 2){
				 pst.setInt(1, startId);
				 pst.setString(2,accountDetails[0]);
				 pst.setInt(3,Integer.parseInt(accountDetails[1]));
				 pst.setString(4,accountDetails[2]);
				// System.out.println("Query : "+qry);
			     pst.addBatch(); 
			 }
			
		 }
		 try{
			 con.setAutoCommit(false);
			 pst.executeBatch();
			 con.commit();
		 }catch(Exception e ){
			 e.printStackTrace();
			 con.rollback();
		 }
		 finally{
				closeConnection(con);
			}

		
		 System.out.println("Total time taken to process is "+(System.currentTimeMillis()-startTime));
		 
		System.out.println("Batch updation done ");
		System.out.println("Inside BatchPreparedStatementSample main() End");
	}
	
	private static int getMaxAccountId(Connection con) throws SQLException{
		 Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery("select max(id) as maxId from account");
		 if(null != rs && rs.next())
			 return rs.getInt("maxId");
		 
		 return 0;
	}
	
	
	private static List<String> loadFile() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Sunny\\Desktop\\batch_insert.txt")));
		List<String> data = new ArrayList<String>();
		String line = null;
		while((line = br.readLine()) != null){
			data.add(line);
		}
		br.close();
		return data;
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
