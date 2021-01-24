package TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class GetBookingID extends BaseClass {

	@BeforeClass
	public void getBookingID() {
		logger.info("*************Started Get Single Booking ID Test Case");
		
		String basePath = "booking/1";

		RestAssured.baseURI = BaseClass.getBookingBaseURI();
		RestAssured.basePath = basePath;
		
		response = given()
				   .when()
				   		.get()
				   .then()
				   		.extract()
				   		.response();
				   
		System.out.println("Response is: "+response.asPrettyString());		
	}

	@Test
	public void testGetBookingID() {

		// Get Status Line
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

		// Get Status Code
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@AfterClass
	public void tearDown() {
	logger.info("********* Single Booking Id Test Case execution is completed **********");
	}

}
