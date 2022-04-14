package com.MyTestRequests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class DeleteRequest {

	@Test
	public void deleteRequestTest() {
		
		Response response = given().header("Content-Type", ContentType.JSON)
				   .baseUri("http://localhost:3000")
				   .pathParam("id", 429)
				   .log()
				   .all()
				   .delete("/employees/{id}");
			
			response.prettyPrint();
	}
}
