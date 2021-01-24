package RestUtils;

import java.io.IOException;

import org.testng.annotations.DataProvider;



public class DataProviderFactory {
	
	@DataProvider(name="CreateBooking")
	static String[][] createBookingData() throws IOException{
		String path = System.getProperty("user.dir")+"\\TestData\\CreateBooking.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String[][] createbooking = new String[rownum][colcount];
		for(int i = 1; i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				createbooking[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return createbooking;
	}
	

}
