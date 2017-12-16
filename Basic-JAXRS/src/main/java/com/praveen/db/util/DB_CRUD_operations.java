/**
 * 
 */
package com.praveen.db.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.swing.DefaultButtonModel;
/**
 * @author Praveen Ambi
 *
 */
public class DB_CRUD_operations {

	/**
	 * 
	 * This method is used to insert data into the Database 
	 * As this is used for the Request for POST method in RestAPI 
	 * 
	 */
	public static void  createRecord() {

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {

			String SQL_Insert = "INSERT INTO ME_ITP_TESTING(Emp_ID,Name,Email_ID)"+ "VALUES(75018,'Kiran Shah','Kiran.Shah@capgenin.com')";
			int execute_update = stmnt.executeUpdate(SQL_Insert);

			if (execute_update==1) {

				System.out.println("Record created for");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * This method is used to read all the data from  into the Database table
	 * As this is used for the Request for GET  method in RestAPI 
	 * 
	 */
	public static ArrayList<String>  readAllRecord(String cloumnName) {

		ResultSet rs = null ;
		ArrayList<String> result_set = new ArrayList<String>();

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {

			//step4 execute query  
			rs=stmnt.executeQuery("select * from ME_ITP_TESTING");  

			while (rs.next()) {

				//System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" \t"+rs.getString(3) + "\t" );  
				//System.out.println(rs.get+"\t");
				result_set.add(rs.getString(cloumnName));

			}

			//System.out.println("the size is ---" +result_set.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_set;

	}

	/**
	 * 
	 * This method is used to read all the data from  into the Database table
	 * As this is used for the Request for GET  method in RestAPI 
	 * 
	 */
	public static int  readNumberof_Rows() {


		int rows=0;

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {

			//step4 execute query  
			ResultSet rs=stmnt.executeQuery("select count(*) from ME_ITP_TESTING");  

			rs.next();
			rows = rs.getInt(1);

			//System.out.println("No of Rows from table " +rows );
			return rows;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;

	}



	public static ResultSet  readSingleRecord(int employeeID) {

		ResultSet rs = null ;

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {

			//step4 execute query  
			rs=stmnt.executeQuery("select * from ME_ITP_TESTING  WHERE EMP_ID =" + "'75018'");  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + "   " );  

			return rs;
		} 
		catch (Exception e) {
			e.printStackTrace();

		}
		return rs;

	}

	
	/**
	 * This method is used to read all the data from  into the Database table
	 * As this is used for the Request for GET  method in RestAPI 
	 */
	public static ArrayList<String>  insertRecord(int EmpID, String Name, String MailID) {

		ResultSet rs = null ;
		ArrayList<String> result_set = new ArrayList<String>();

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {

			java.util.StringJoiner joiner = new  java.util.StringJoiner("','", "'", "'");
			joiner.add(String.valueOf(EmpID));
			joiner.add(Name);
			joiner.add(MailID);
			System.out.println(joiner);
			//step4 execute query  
			rs=stmnt.executeQuery("INSERT INTO ME_ITP_TESTING (Emp_ID,Name,Email_ID) VALUES("+ joiner +")");  
			
			System.out.println("The given Record is inserted into the table with the values" + joiner);

			//System.out.println("the size is ---" +result_set.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_set;

	}

	public static ArrayList<String>  updateRecord(int EmpID, String Name, String MailID) {

		ResultSet rs = null ;
		ArrayList<String> result_set = new ArrayList<String>();

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {
			
			java.util.StringJoiner joiner = new  java.util.StringJoiner("','", "'", "'");
			joiner.add(String.valueOf(EmpID));
			joiner.add(Name);
			joiner.add(MailID);
			System.out.println(joiner);

			//step4 execute query  
			rs=stmnt.executeQuery("UPDATE ME_ITP_TESTING SET   NAME='"+Name+"',EMAIL_ID='"+MailID+"' WHERE EMP_ID="+ EmpID);  
			
			
			System.out.println("The given Record is updated into the table ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_set;

	}
	
	public static String  deleteRecord(int EmpID) {

		ResultSet rs = null ;
		ArrayList<String> result_set = new ArrayList<String>();
		String Message = null;

		try(Connection connection = DatabaseUtil.getConnection(); Statement stmnt = connection.createStatement();) {

			//step4 execute query  
			rs=stmnt.executeQuery("DELETE from ME_ITP_TESTING WHERE EMP_ID=" + EmpID );  
			
			System.out.println("The given Record is deleted from the table ");
			
			 Message = "The Employee record with Employee ID " + EmpID + "is removed";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message;
	}


	public static void main(String[] args) throws SQLException {

		//DB_CRUD_operations.createRecord();
		//DB_CRUD_operations.readAllRecord("EMP_ID");
		//DB_CRUD_operations.readAllRecord().getString(2);
		//DB_CRUD_operations.readAllRecord().getString(3);
		//DB_CRUD_operations.readNumberof_Rows();
		//DB_CRUD_operations.insertRecord(33333, "Shahira Kriti", "shahira.kriti@capgemini.com" );
		//DB_CRUD_operations.updateRecord(33333, "Rubina Mala", "rubina.mala@capgemini.com");
		DB_CRUD_operations.deleteRecord(74120);
		

	}

}
