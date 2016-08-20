package com.sun.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Sunny
 * utility class for Database
 *
 */
public class DBUtil {

	
	
	/**
	 * open connection
	 * @return
	 * @throws SQLException
	 */
	public static Connection openConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","sunilm");
		//return OracleDBUtil.openConnection();
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
