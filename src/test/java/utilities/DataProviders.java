package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

//Purpose of this DataProviders Class - It will read data from Excel and store in 2 diamentional Array and it will pass to Testcase
//ExcelUtility will be used in DataProviders

public class DataProviders {
	
	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testdata\\testdata.xlsx";  //Taking location in variable to take  data from Excel
		
		//Creating an Object for ExcelUtility Class
		ExcelUtility xlutil = new ExcelUtility(path); //passing path(Location) of ExcelUtility constructor
		
		int totalRows = xlutil.getRowCount("sheet1");   //Invloking getRowCount method from ExcelUtility Class
		int totalCols = xlutil.getCellCount("sheet1", 1);  //Invloking getCellCount method from ExcelUtility Class, 1 representing Row Number
		
		String loginData[][] = new String[totalRows][totalCols]; //Created 2 diamention Array which can store Excel data
		
		for(int i=1; i<=totalRows; i++)  //Row index start from 1, Avoiding Header, Reading Data from Excel sheet and copying in 2 diamentonal Array
		{
			for(int j=0; j<totalCols; j++)  //Col index start from 0
			{
				loginData[i-1][j] = xlutil.getCellData("sheet1", i, j);  //Getting row and col data and storing in 2 Diamentinal, [i-1] - Array start from 0,//1,0
			}
		}
		
		return loginData;  //Returning two diamentional Array
	}
	
	//For Other Test method we can keep add more here
	//Data Provider 1
	
	//Data Provider 2
	
	//Data Provider 3

}
