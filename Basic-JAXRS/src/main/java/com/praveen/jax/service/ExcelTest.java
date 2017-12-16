package com.praveen.jax.service;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.DataProvider;

public class ExcelTest {

	//private static final Object Emp_ID ;
	static Map<String,String> dMap;
	static File file = new File("D:/Praveen_86061FS/Basic-JAXRS/Summation.xls");
	
	static ExcelReadWrite xl =null;
	static Sheet Sheet1 ;
	static int rowCount;
	
	
	
	/*static String path = System.getProperty("user.dir")+"/Summation.xls";
	static File file=new File(path);*/
	//static String dataFile ="Summation.xls";
	//static URL url = ExcelTest.class.getClassLoader().getResource("Summation.xls");

	//xl = new ExcelReadWrite(file.getAbsolutePath());
	
	
	
	
	
	
	@DataProvider(name="dp_sum")
	public  static int getRowCount()  {

		try {

			xl = new ExcelReadWrite(file.getAbsolutePath());
			Sheet1 = xl.Setsheet("Sheet1");

			rowCount = xl.getrowcount(Sheet1);

		//	System.out.println("datafile rows are ---------"+ rowCount);

		} catch (Exception e) {
			e.printStackTrace();
		}


	return rowCount;

	}


	@DataProvider(name="dp_sum")
	public  static String testID()  {
		try {

			xl = new ExcelReadWrite(file.getAbsolutePath());
			Sheet1 = xl.Setsheet("Sheet1");

			rowCount = xl.getrowcount(Sheet1);

			for(int i = 1; i<=rowCount;i++)
			{
				dMap = new HashMap<String,String>();

				dMap.put(xl.Readvalue(Sheet1, 0, "Emp_ID"), xl.Readvalue(Sheet1, i, "Emp_ID"));
				/*dMap.put(xl.Readvalue(Sheet1, 0, "Num2"), xl.Readvalue(Sheet1, i, "Num2"));
			dMap.put(xl.Readvalue(Sheet1, 0, "Expected_result"), xl.Readvalue(Sheet1, i, "Expected_result"));*/

				System.out.println("Employee ids are-------------- " + dMap.get("Emp_ID"));
			}

			return dMap.get("Emp_ID").toString();	

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dMap.get("Emp_ID").toString();	

	}

	@DataProvider(name="dp_sum")
	public  static String testName()  {
		try {
			xl = new ExcelReadWrite(file.getAbsolutePath());
			Sheet1 = xl.Setsheet("Sheet1");

			rowCount = xl.getrowcount(Sheet1);

			for(int i = 1; i<=rowCount;i++)
			{
				dMap = new HashMap<String,String>();

				//dMap.put(xl.Readvalue(Sheet1, 0, "Num1"), xl.Readvalue(Sheet1, i, "Num1"));
				dMap.put(xl.Readvalue(Sheet1, 0, "name"), xl.Readvalue(Sheet1, i, "name"));
				//dMap.put(xl.Readvalue(Sheet1, 0, "Expected_result"), xl.Readvalue(Sheet1, i, "Expected_result"));

				//System.out.println("Employee names are ------------" + dMap.get("name"));

			}

			return dMap.get("name").toString();	

		} catch (Exception e) {

			e.printStackTrace();

		}
		return dMap.get("name").toString();	
	}

	@DataProvider(name="dp_sum")
	public  static String testlastName()  {
		try {

			xl = new ExcelReadWrite(file.getAbsolutePath());
			Sheet1 = xl.Setsheet("Sheet1");

			rowCount = xl.getrowcount(Sheet1);

			for(int i = 1; i<=rowCount;i++)
			{
				dMap = new HashMap<String,String>();

				/*dMap.put(xl.Readvalue(Sheet1, 0, "Num1"), xl.Readvalue(Sheet1, i, "Num1"));
			dMap.put(xl.Readvalue(Sheet1, 0, "Num2"), xl.Readvalue(Sheet1, i, "Num2"));*/
				dMap.put(xl.Readvalue(Sheet1, 0, "last_name"), xl.Readvalue(Sheet1, i, "last_name"));
				//System.out.println("Employee last names are-----------" + dMap.get("last_name"));
			}

			return dMap.get("last_name").toString();	

		} catch (Exception e) {

			e.printStackTrace();
		}	
		return dMap.get("last_name").toString();	
	}

	public static void main(String[] args) throws Exception  {

		ExcelTest.testID();
		ExcelTest.testName();
		ExcelTest.testlastName();
		ExcelTest.getRowCount();
		xl = new ExcelReadWrite(file.getAbsolutePath());
		
		/*System.out.println("data sheet row  count is -----"+  xl.getrowcount(xl.Setsheet("Data")));
		System.out.println("data sheet is -----"+  xl.Setsheet("Data").getSheetName());
		System.out.println("data sheet is -----"+  xl.Setsheet("Data"));
		*/
		
		//xl.writecell(xl.Setsheet("Data"), xl.getrowcount(xl.Setsheet("Data"))+1, "Emp_ID", "25");
		//ExcelReadWrite.writeXLSXFile("Data", xl.getrowcount(xl.Setsheet("Data"))+1, "Emp_ID", "30");
	}



}
