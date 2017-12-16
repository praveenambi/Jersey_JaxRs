package com.praveen.jax.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.praveen.jax.service.ExcelReadWrite;
import com.praveen.db.util.DB_CRUD_operations;
import com.praveen.jax.databse.Database;
import com.praveen.jax.exception.DataNotFoundException;
import com.praveen.jax.model.Message;

public class MessageService {

	private Map<Long,  Message> msges = Database.getMessages();
	static File file = new File("D:/Praveen_86061FS/Basic-JAXRS/Summation.xlsx");
	static ExcelReadWrite xl  ;

	public MessageService()  {
		
		/**
		 * Code Driving the data from the database  ME_ITP_TESTING table  
		 * 
		 */

		System.out.println("The number of rows in tha table are ---" + DB_CRUD_operations.readNumberof_Rows());

		for (int i = 0; i <DB_CRUD_operations.readNumberof_Rows(); i++) {

			//msges.put(Long.valueOf(i), new Message(Long.parseLong(ExcelTest.testID().trim().replace(".0", "")), ExcelTest.testName(), ExcelTest.testlastName()));

			try {
				msges.put(Long.parseLong(DB_CRUD_operations.readAllRecord("EMP_ID").get(i)), new Message(Long.parseLong(DB_CRUD_operations.readAllRecord("EMP_ID").get(i)),DB_CRUD_operations.readAllRecord("NAME").get(i),DB_CRUD_operations.readAllRecord("EMAIL_ID").get(i)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			//System.out.println("messages size is --------" +msges);
		}

		/**
		 * 
		 * Important code code for Jax-rs 
		 * 
		 */
		/*msges.put(1l, new Message(1, "Hello ME bank", "Praveen"));

		msges.put(2l, new Message(2, "Hello ME bank.......How are you ?.....!", "Praveen "));


		System.out.println("My messages are -------" + msges);*/



	}
	
	
	public List<Message> getMessages() {

		Message msg = new Message(1, "Hey ME bank..........!", "Praveen");

		Message msg_sec = new Message(2, "Hey ME bank..........How are you ? ....!", "Praveen");


		List<Message> msg_list = new ArrayList<>();
		msg_list.add(msg);
		msg_list.add(msg_sec);
		return msg_list;


	}


	public List<Message> getallMessages() {

		return new ArrayList<Message>(msges.values());


	}


	public Message getmessage(long id) {
		 Message mesg = msges.get(id);
		 
		 if (mesg==null) {
			 
			 throw new DataNotFoundException("Exployee Record by id  " + id+ "  not found");
			
		}

		 return mesg;
	}


	public Message addMessage(Message msg) {
		
		//msg.setEmpID(msges.size()+1);
		
		Long empid = msg.getEmpID();
		
		DB_CRUD_operations.insertRecord(empid.intValue(), msg.getName(), msg.getEmailID());
		
		msges.put(msg.getEmpID(), msg);
		return  msg;
		
		
		
	}

	/**
	 * 
	 * The code is written for Update  functionality 
	 * 
	 * 
	 */
	public Message updateMessage(Message msg) {
		if (msg.getEmpID()==0) {
			return null;
		}
		
		Long empid = msg.getEmpID();
		
		DB_CRUD_operations.updateRecord(empid.intValue(), msg.getName(), msg.getEmailID());

		msges.put(msg.getEmpID(), msg);
		return  msg;

	}

	public Message removeMessage(long id ) {
		
		Long empid =id;
		DB_CRUD_operations.deleteRecord(empid.intValue());

		return  msges.remove(id);

	}

	
	/**
	 * 
	 * The code is written for Filtering  functionality  by year 
	 * 
	 * 
	 */
public List<Message> getAllMessagesForYear(int  year ) {
	
	
	List<Message> messageForyear = new ArrayList<>();
	Calendar calendar = Calendar.getInstance();
	for (Message msg : msges.values()) {
		
		calendar.setTime(msg.getCreated());
		
		if (calendar.get(Calendar.YEAR)==year) {
			
			messageForyear.add(msg);
			
		}
		
	}
	return messageForyear;
	
}

/**
 * 
 * The code is written for pagination functionality 
 * 
 * by index 
 */

public List<Message> getAllMessagesPaginated(int  start,int size ) {
	
	ArrayList<Message> msgList = new ArrayList<Message>(msges.values());
	
	if (start+size >= msgList.size()) {
		
		return new ArrayList<Message>();
		
	}
	return msgList.subList(start, start+size);
			
}

}

