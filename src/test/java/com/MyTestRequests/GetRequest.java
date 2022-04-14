package com.MyTestRequests;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GetRequest {

	// Rest Assured supports both BDD as well as Non BDD
	// Sugar coated methods in Java

	@Test(enabled = false)
	public void getMethodTest() {

		Response response = given().get("http://localhost:3000/employees");

		response.prettyPrint();

		System.out.println(response.getStatusCode());

		System.out.println(response.getContentType());

		System.out.println(response.header("Content-Type"));

		Headers header = response.getHeaders();

		for (Header header2 : header) {

			System.out.println(header2.getName() + ":" + header2.getValue());

		}

		System.out.println(response.time());

		System.out.println(response.timeIn(TimeUnit.SECONDS));

	}

	@Test
	public void getMethodWithQueryParametersTest() {

		Response response = given()
				.queryParam("id", 831)
				.log()
				.all()
				.get("http://localhost:3000/employees");
				

		response.prettyPrint();
	}
}
