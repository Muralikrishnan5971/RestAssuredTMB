package com.MyTestRequests;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UpdateRequest {

	@Test
	public void updateRequestTest() {

		JSONObject requestObj = new JSONObject();
		requestObj.put("firstname", "Commisioner");
		requestObj.put("lastname", "Gordon");
		
		Response response = given().header("Content-Type", ContentType.JSON)
			   .baseUri("http://localhost:3000")
			   .pathParam("id", 429)
			   .log()
			   .all()
			   .body(requestObj.toMap())
			   .put("/employees/{id}");
		
		response.prettyPrint();
	}

}
