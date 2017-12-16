package com.praveen.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseUtil {

	private static final  String DB_DRIVERCLASS = "oracle.jdbc.driver.OracleDriver";
	private static final  String DB_USERNAME  = "Praveen";
	private static final  String DB_PASSWORD ="India+++";
	private static final  String DB_URL = "jdbc:oracle:thin:@din38000018:1521:xe";

	private static Connection connect = null;

	public static Connection  getConnection() {
		try {
			
			Context initialContext= new InitialContext();
			Context env_context= (Context) initialContext.lookup("java:comp/env");
			DataSource  datasrc_obj = (DataSource) env_context.lookup("java:jboss/jdbc/postgres");
			//DataSource  datasrc_obj = (DataSource) env_context.lookup("java:OracleDS");
			Class.forName(DB_DRIVERCLASS);
			//connect= DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
			connect= datasrc_obj.getConnection();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return connect;
	}

	/*public static void main(String args[]){  
		try{  

			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@din38000018:1521:xe","Praveen","India+++");  

			//step3 create the statement object  
			Statement stmt=con.createStatement();  

			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select * from ME_ITP_TESTING");  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + "   " );  

			//step5 close the connection object  
			con.close();  

		}catch(Exception e){

			System.out.println(e);

		}  

	}  */
}  



