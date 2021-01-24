package TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import RestUtils.XLUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class CreateBooking extends BaseClass {

	@Test(dataProvider = "CreateBooking")
	public void createBooking(String firstname, String lastname, String totalprice, String depositpaid, 
			String checkin, String checkout, String additionalneeds) {

		logger.info("*************Started Execution of Booking Test Case");

		String basePath = "booking";

		RestAssured.baseURI = BaseClass.getBookingBaseURI();
		RestAssured.basePath = basePath;

		// Request Object
		// httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("firstname", firstname);
		requestParams.put("lastname", lastname);
		requestParams.put("totalprice", totalprice);
		requestParams.put("depositpaid", depositpaid);
		Map<String, String> bookingDates = new HashMap<>();
		bookingDates.put("checkin", checkin);
		bookingDates.put("checkout", checkout);
		requestParams.put("bookingdates", bookingDates);
		requestParams.put("additionalneeds", additionalneeds);

		response = given().contentType("application/json").body(requestParams).when().post().then().extract()
				.response();

		// Print Response as a string
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body is: " + responseBody);

		// getting booking id
		JsonPath jsonPath = response.jsonPath();
		int bID = jsonPath.get("bookingid");
		System.out.println("Booking ID is: " + bID);

		// Get Status Line
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

		// Get Status Code
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@DataProvider(name = "CreateBooking")
	static Object[][] createBookingData() throws IOException {
		String path = System.getProperty("user.dir") + "\\TestData\\CreateBooking.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		Object[][] createbooking = new Object[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				createbooking[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return createbooking;
	}

	@AfterClass
	public void tearDown() {
		logger.info("********* Create Booking Test Case execution is completed **********");
	}

}
