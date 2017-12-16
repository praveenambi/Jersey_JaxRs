package com.praveen.jax.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.DataProvider;

public class Summation_dataprovider {

	
	

	@DataProvider(name="dp_sum")
	public static Iterator<Object[]> getsummationdata() throws Exception
	{
		
		ExcelReadWrite xl = new ExcelReadWrite("./Summation.xls");
		
		Sheet Sheet1 = xl.Setsheet("Sheet1");
		
		int rowCount = xl.getrowcount(Sheet1);
		System.out.println("No of rows = " +rowCount);
		
		List<Object[]> Array_list = new ArrayList<Object[]>();
		
		
		
		for(int i = 1; i<=rowCount;i++)
		{
			Map<String,String> dMap = new HashMap<String,String>();
			
			dMap.put(xl.Readvalue(Sheet1, 0, "Emp_ID"), xl.Readvalue(Sheet1, i, "Emp_ID"));
			dMap.put(xl.Readvalue(Sheet1, 0, "name"), xl.Readvalue(Sheet1, i, "name"));
			dMap.put(xl.Readvalue(Sheet1, 0, "last_name"), xl.Readvalue(Sheet1, i, "last_name"));
			
			Object[] obj = new Object[1];
			obj[0]=dMap;
			
			System.out.println("Excel object is -+-------" + obj[0]);
			Array_list.add(obj);
			
		}
		
		 return Array_list.iterator();
	}
	
		
	
	
	
}
