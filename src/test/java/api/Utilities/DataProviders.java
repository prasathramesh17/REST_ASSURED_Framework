package api.Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="data")
	public String[][] getData() throws IOException
	
	{
		String path = System.getProperty("user.dir")+"//testData//ddtrestassured.xlsx";
		
		XLUtility Xl= new XLUtility(path);
		
		int rownum = Xl.getRowCount("Sheet1");
		
		int colcount = Xl.getCellCount("Sheet1", 1);
		
		String apidata[][] = new String[rownum] [colcount];
		
		for(int i = 1;i<=rownum;i++)
		{
			for(int j=0; j<colcount;j++)
			{
				apidata[i-1][j] =Xl.getCellData("Sheet1", i, j);
				
			}
		}
		
		return apidata;
		}
	}
