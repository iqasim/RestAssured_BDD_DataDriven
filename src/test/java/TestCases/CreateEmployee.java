package TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import RestUtils.RestUtils;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class CreateEmployee extends BaseClass{
	
	@BeforeClass
	public void createEmployee() {
		
		logger.info("********* Started Creating Employee **********");
		
		String basePath = "create";
		RestAssured.baseURI = BaseClass.getEmpBaseURI();
		RestAssured.basePath = basePath;
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", RestUtils.getEmpName());
		requestParams.put("salary", RestUtils.getEmpSal());
		requestParams.put("age", RestUtils.getEmpAge());
		
		response = given()
			.contentType("application/json")
			.body(requestParams)
		.when()
			.post()
		.then()
			.extract()
			.response();
		
		System.out.println(response.asPrettyString());
		
		 
	}
	
	@Test
	public void testCreateEmployee() {
		
		//Asserting status code
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@AfterClass
	public void tearDown() {
	logger.info("********* Create Employee Test Case execution is completed **********");
	}

}
