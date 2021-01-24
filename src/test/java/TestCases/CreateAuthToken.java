package TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

public class CreateAuthToken extends BaseClass {

	@BeforeClass
	public void createToken() {
		
		logger.info("***********Creating Auth Token*******************");

		String basePath = "auth";

		RestAssured.baseURI = BaseClass.getBookingBaseURI();
		RestAssured.basePath = basePath;

		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "admin");
		requestParams.put("password", "password123");

		
		response = given()
			.contentType("application/json")
			.body(requestParams)
		.when()
			.post()
		.then()
			.extract()
			.response();
		
		
		// Print Response as a string
		String responseBody = response.getBody().asPrettyString();
		System.out.println("Response body is: " + responseBody);

		// getting token

		JsonPath jsonPath = response.jsonPath();
		String token = jsonPath.get("token");
		System.out.println("Token is: " + token);

	}

	@Test
	public void testPostCreateToken() {

		// Get Status Line
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");

		// Get Status Code
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@AfterClass
	public void tearDown() {
	logger.info("********* Auth Token Test Case execution is completed **********");
	}

}
