package com.sun.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.java.jdbc.model.Account;
import com.sun.java.jdbc.util.DBUtil;


public class AccountTrans {

	
	public static void main(String[] args)throws Exception{
		
		System.out.println("Transaction Starting here ...");
		
		if(null == args || args.length < 3){
			System.err.println("Invalid Inputs to start money transaction ....");
			System.exit(0);
		}
		
		int sourceAccountId = Integer.parseInt(args[0]);
		int transferAmount =  Integer.parseInt(args[1]);
		int targetAccountId = Integer.parseInt(args[2]);
		
		Account sourceAccount = loadAccountById(sourceAccountId);
		
		if(transferAmount > sourceAccount.getBalance()){
			System.err.println("InValid Balance to transfor");
			System.exit(0);
		 }
		
		double beforeRemoveBal  = sourceAccount.getBalance();
		double afterRemoveBal = beforeRemoveBal-transferAmount;
		
		
		
		Account targetAccount = loadAccountById(targetAccountId);
		double beforeAddinBal  = targetAccount.getBalance();
		double afterAddinBal = beforeAddinBal+transferAmount;
		
		Connection con = DBUtil.openConnection();
		con.setAutoCommit(false);	// changing connection into non auto commit mode	
		try{
			System.out.println("Inside try block ");
			updateBalance(sourceAccount.getAccountNumber(),afterRemoveBal,con);
			System.out.println(" amount deleted from source account");
			
			updateBalance(targetAccount.getAccountNumber(),afterAddinBal,con);
			System.out.println(" amount added to target account");
			
			con.commit();
			System.out.println("Commit the changes");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception while doing money trafor ");
			con.rollback();
			System.out.println("Rolled back the changes ");
		}finally{
			DBUtil.closeConnection(con);
		}
		
		System.out.println("Transaction Completed Succesfully");
		
		
	}
	
    /**
     * 
     * @param accountId
     * @param amount
     * @param con
     * @return boolean
     * @throws SQLException
     * 
     * this method updates the balance with passed arguments
     */
	private static boolean updateBalance(int accountId,double amount,Connection con) throws SQLException{
		Statement st = con.createStatement();
		String qry = "update customer_trans set balance= "+amount+" where account_number = "+accountId;
		  int rowCount  = st.executeUpdate(qry);
		  if(rowCount > 0)
			  return true;
		 
		return false;
	}
	
	
	/**
	 * @author Sunil M 
	 * @param accountId
	 * @return Account object
	 * @throws SQLException
	 * this method loads account object from db by passing account id as an argument 
	 * 
	 */
	private static Account loadAccountById(int accountId) throws SQLException{
		Connection con = DBUtil.openConnection();
		Account account = new Account();
		try{
		Statement st = con.createStatement();
		String qry = "select * from customer_trans where account_number = "+accountId;
		ResultSet rs = st.executeQuery(qry);
		 if(rs.next()){
				account.setCustId(rs.getInt("cust_id"));
				account.setCustName(rs.getString("cust_name"));
				account.setAccountNumber(rs.getInt("account_number"));
				account.setBalance(rs.getDouble("balance"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return account;
	}
	
	
	
}
