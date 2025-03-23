package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		
		String path = System.getProperty("user.dir")+"\\testData\\testData.xlsx";
		Xls_Reader reader= new Xls_Reader(path);
		int rowsCount = reader.getRowCount("Sheet1");
		int colsCount = reader.getCellCount("Sheet1",1);
		String loginData[][] = new String[rowsCount][colsCount];
		for(int i=1;i<=rowsCount;i++) {
			for(int j=0;j<colsCount;j++) {
				loginData[i-1][j] = reader.getCellData("Sheet1",i, j);	
			}
		}
		return loginData;  // returning two Dimensional Data
	}

}
