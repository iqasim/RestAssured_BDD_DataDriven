package TestCases.pojo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import RestUtils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CreateBook {

	public Response response;
	public int id = RestUtils.getID();
	
	
	@BeforeClass
	public void testPOJO() throws JsonProcessingException {
		RestAssured.baseURI= "http://localhost:3000/";
		RestAssured.basePath = "posts";

		CreateBookParams cbp = new CreateBookParams(id, "Test345", "Ayesha");
		
		response = given()
			.contentType(ContentType.JSON)
			.body(cbp)
		.when()
			.post()
		.then()
			.extract()
			.response();
				
		System.out.println("Response is "+response.asPrettyString());
		
		/*
		 * 
		 * ObjectMapper objMap = new ObjectMapper();
		 * 
		 * String data =
		 * objMap.writerWithDefaultPrettyPrinter().writeValueAsString(cbp);
		 * 
		 * System.out.println(data);
		 */
		
	}
	
	@Test
	public void teststatus()
	{
		Assert.assertEquals(response.getStatusCode(), 201); 
	}

	/*
	 * @Test public void testBookData() { CreateBookParams cb =
	 * get("http://localhost:3000/posts").as(CreateBookParams.class);
	 * System.out.println(cb.toString()); }
	 */

}
