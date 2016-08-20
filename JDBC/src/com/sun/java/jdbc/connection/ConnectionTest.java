package com.sun.java.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionTest {

	
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	
	//case 1 start
	private static final String URL = "jdbc:mysql://localhost:3306/test";
	private static final String PASS_WORD = "sunilm";
	private static final String USER_NAME = "root";
	//case1 end
	
	//case 2 start
	//private static final String URL = "jdbc:mysql:root/sunilm://localhost:3306/test";
	//case2 end
	
	//case 3 start
		//private static final String URL = "jdbc:oracle:thin:@localhost:1521:oracle";
	//case3 end
	
	
	//H:\app\oracle\product\12.1.0\dbhome_1\jdbc\lib
	public static void main(String[] args) throws Exception{
		Class.forName(DRIVER_CLASS_NAME);
		
		//case 1
		Connection con = DriverManager.getConnection(URL,USER_NAME,PASS_WORD);
		
		//case 2 
		//Connection con = DriverManager.getConnection(URL);
		
		//case 3
		/*Properties props = new Properties();
		props.put("user","SYSTEM");
		props.put("password","oracle");
		Connection con = DriverManager.getConnection(URL,props);
		*/
		
		System.out.println(" Driver Impl Class : "+con.getClass().getName());
	}
}
