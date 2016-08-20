package com.sun.java.jdbc.callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.java.jdbc.util.DBUtil;

public class CallableTest {
	
	public static void getCandidateDetails(int candidateId) throws SQLException {
		
	 String query = "{ call get_candidate_skill(?) }";
	 Connection con = null;
	 try{
		 con = DBUtil.openConnection();
		 CallableStatement stmt = con.prepareCall(query);
		 stmt.setInt(1, candidateId);
 
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s - %s",
                        rs.getString("first_name") + " "
                        + rs.getString("last_name"),
                        rs.getString("skill")));
            }
	 }catch(Exception e){
		 e.printStackTrace();
	 }finally{
		 DBUtil.closeConnection(con);
	 }
	}

	
	public static void main(String[] args) throws SQLException {
		getCandidateDetails(444);
	}
}
