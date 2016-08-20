package com.sun.java.jdbc.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BatchStatementSample {

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("Inside BatchStatementSample main() Starts");
		List<String> dataList = loadFile();//loading data from File
		Connection con = openConnection();
		Statement st = con.createStatement();
		int startId = getMaxAccountId(con);//getting max id from table
		for (String line : dataList) {
			startId++;
			String[] accountDetails = line.split("\\|");
			if (null != accountDetails && accountDetails.length > 2) {
				String qry = "insert into account values (" + startId + ",'"
									+ accountDetails[0] + "'," + accountDetails[1] + ",'"
									+ accountDetails[2] + "')";
				//System.out.println("Query : " + qry);
				st.addBatch(qry);
			}

		}//end for 
		
		try {
			con.setAutoCommit(false);
			st.executeBatch();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}finally{
			closeConnection(con);
		}

		System.out.println("Total time taken to process is "+ (System.currentTimeMillis() - startTime));

		System.out.println("Batch updation done ");
		System.out.println("Inside BatchStatementSample main() End");
	}

	private static int getMaxAccountId(Connection con) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select max(id) as maxId from account");
		if (null != rs && rs.next())
			return rs.getInt("maxId");

		return 0;
	}

	private static List<String> loadFile() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"C:\\Users\\Sunny\\Desktop\\batch_insert.txt")));
		List<String> data = new ArrayList<String>();
		String line = null;
		while ((line = br.readLine()) != null) {
			data.add(line);
		}
		br.close();
		return data;
	}

	/**
	 * open connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection openConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "sunilm");
	}

	/**
	 * closes connection
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws SQLException {
		if (null != con) {
			con.close();
		}
	}
}
