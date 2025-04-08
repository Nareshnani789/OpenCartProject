package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Dtaprovider1
	@DataProvider(name="LoginData")
	public String[][]getdata() throws IOException
	{
		String path=".\\testdata\\Opencart_LoginData.xlsx";//taking x1 file from data
		
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getcellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols]; //created for two dimension array which can store
		
		for(int i=1;i<=totalrows;i++) //1 //read the data from xlstoring in two dimensional array
		{
			for(int j=0;j<totalcols;j++) //0 i is rows j is col
			{
				logindata[i-1][j]=xlutil.getCelldata("Sheet1", i, j);  //1,0
			}
		}
		return logindata; //returning two dimensional array
		
	}
	
	//Dataprovider2
	
	//Dtaprovider3
	

}
