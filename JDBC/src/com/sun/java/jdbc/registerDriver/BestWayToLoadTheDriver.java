package com.sun.java.jdbc.registerDriver;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Registering driver is nothing but loading driver class into JVM memory.
 * @author  : Sunny
 * @date    : 14-May-2016
 * @version : 1.0
 *
 */
public class BestWayToLoadTheDriver {
	
	
	/**
	 * The name of the driver class it based on type of driver we use 
	 * and type of Database we are going to connect
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		//Class forName method throws ClassNotFoundExcpetion.. we should 
		//Class.forName("oracle.jdbc.driver.OracleDriver");// oracle Data base
		Class.forName("com.mysql.jdbc.Driver");//My sql Data Base
		
		//Advanced
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while(drivers.hasMoreElements()){
			System.out.println(" -------------------------- ");
			System.out.println(drivers.nextElement());
		}
	}

}
