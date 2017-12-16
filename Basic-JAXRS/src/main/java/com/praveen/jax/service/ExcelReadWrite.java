package com.praveen.jax.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

		public class ExcelReadWrite {
			
			FileInputStream fis;
			static Workbook wb;
			static FileOutputStream fileOut;
			
			
			public ExcelReadWrite(String xlPath) throws IOException, InvalidFormatException
			{
				 fis= new FileInputStream( xlPath);
				
				//workbook object
				 wb= WorkbookFactory.create(fis);
				 
				 fileOut = new FileOutputStream(xlPath);
				 
				 
				
			}
						
			public Sheet Setsheet(String sheetname)
			{
				Sheet Sheet = wb.getSheet(sheetname);
				return Sheet;
				
			}
			
			
			public int getrowcount(Sheet Sheet)
			{
				int lastRowNum = Sheet.getLastRowNum();
				return lastRowNum;
				
			}
			
			public static int getcolcount(Sheet Sheet,int rowIndex)
			{
				int lastcolnum  = Sheet.getRow(rowIndex).getLastCellNum();
				return lastcolnum;
				
			}
			
			public String Readvalue(Sheet Sheet,int rowIndex, int cellnum)
			{
				Cell cell = Sheet.getRow(rowIndex).getCell(cellnum);
				
				String celltext=null;
				
				if(cell==null)
				celltext="";
				
				else if(cell.getCellType()==cell.CELL_TYPE_STRING)
				celltext=cell.getStringCellValue();
				
				else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
				celltext=String.valueOf(cell.getNumericCellValue());
				
				else if(cell.getCellType()==cell.CELL_TYPE_BLANK)
				celltext="";
				
				return celltext;
			}
			
			
			public String Readvalue(Sheet Sheet,int rowIndex, String colname)
			{
				
				int colindex = 0;
				for(int i=0;i<getcolcount(Sheet,0);i++){
					
					
					//System.out.println(row.getCell(i).getStringCellValue().trim());
					if(Sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname))
						colindex=i;
				}
				

				
				return Readvalue(Sheet,rowIndex, colindex);
				
				
			}
			public void writecell(Sheet Sheet,int rowIndex,int cellnum, String wvalue )
			{
				//writing the cell
				Cell writecell = Sheet.getRow(rowIndex).getCell(cellnum);
				if(writecell==null)
				{
					writecell=Sheet.getRow(rowIndex).createCell(cellnum);
				}
				
				writecell.setCellValue(wvalue);
			}
			
			public void writecell(Sheet Sheet,int rowIndex,String colname, String wvalue ){
				int colindex = 0;
				for(int i=0;i<getcolcount(Sheet,0);i++){
					
					
					//System.out.println(row.getCell(i).getStringCellValue().trim());
					if(Sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname))
						colindex=i;
				}
				

				writecell(Sheet,rowIndex,colindex, wvalue );
				
				
				
				
			}
			
			
			public static void writeXLSXFile(String Sheet,int rowIndex,String colname, String wvalue ) throws IOException {
				
				//String excelFileName = "C:/Test.xlsx";//name of excel file
				
				

				String sheetName = "Data";//name of sheet

				 
				 Sheet sheet = wb.getSheet(Sheet) ;

				
				
				int colindex = 0;
				for(int i=0;i<getcolcount(sheet,0);i++){
					
					
					//System.out.println(row.getCell(i).getStringCellValue().trim());
					if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname))
						colindex=i;
				}
				
					Row row = sheet.createRow(rowIndex);

					//iterating c number of columns
					
						Cell cell = row.createCell(colindex);
			
						cell.setCellValue(wvalue);
								

			

				//write this workbook to an Outputstream.
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
			
			
			
			
			
			
			public void save_excel(String xlPath) throws IOException
			{
				fis.close();
				FileOutputStream fos= new FileOutputStream(xlPath);		
				wb.write(fos);
				fos.close();
				
			}
			
			


		}
