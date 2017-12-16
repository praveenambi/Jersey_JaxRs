package com.praveen.jax.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_Excel {
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		//create JDBc conn object
		//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection  conn = DriverManager.getConnection("jdbc:ucanaccess://D:\\Praveen_86061FS\\Basic-JAXRS\\Summation.xlsx;jackcessOpener=com.praveen.jax.service.CryptCodecOpener" ,"", "");
		
		//create the statement 
		Statement stmnt = conn.createStatement();
		
		ResultSet result = stmnt.executeQuery("select * from [Sheet1$]");
		
		while (result.next()) {
			
			System.out.println(result.getInt(1) + "\t" + result.getString(2) + "\t" +result.getString(3) );
			
			
		}
		
		result.close();
		stmnt.close();
		conn.close();
	}
	
	
	

}
