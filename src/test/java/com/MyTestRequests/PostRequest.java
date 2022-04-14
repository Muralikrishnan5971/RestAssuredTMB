package com.MyTestRequests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PostRequest {

	// There are different ways to construct a request body for a post request
	
	// 1. Passing json body as string --> NOT RECOMMENDED
	
	@Test(enabled = false)
	public void postMethodWithStringTypeRequestBody() {
		
		String requestBody = "{\r\n" + 
				"      \"id\": 568,\r\n" + 
				"      \"firstname\": \"murali\",\r\n" + 
				"      \"lastname\": \"krishnan\",\r\n" + 
				"      \"email\": \"testermk@gmail.com\"\r\n" + 
				"}";
		
		
		Response response  = given()
				.header("Content-Type", ContentType.JSON)
				.body(requestBody)
				.log().all()
				.post("http://localhost:3000/employees");
				
		
		response.prettyPrint();
		
		System.out.println(response.getStatusCode());
		
		System.out.println(response.getTime());
	}
	
	// 2. Passing json body from exterenal file
	// a. you cant read the request body from the external file and print it on the console
	// b. use this only for the static data
	
	@Test(enabled = false)
	public void postMethodwithRequestBodyFromExternalFile() {
		
		Response response = given()
				.header("Content-Type", ContentType.JSON)
				.log()
				.all()
				.body(new File(System.getProperty("user.dir")+"/postRequestTestData.json"))
				.post("http://localhost:3000/employees");
		
		response.prettyPrint();
				
	}
	
	// 3. Read the request body from the external file and convert it to String
	// a. logs the request body
	// b. changes few parameters in the body
	
	@Test(enabled = false)
	public void postMethodwithRequestBodyFromExternalFileAndConvertToString() throws IOException {
		
		// java.nio.file.Files
		
		byte[] byteStream = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/postRequestTestData.json")); // convert json file to byte stream
		
		String requestBody = new String(byteStream); // converts our byte stream to string
		
		String latestRequestBody = requestBody.replace("1", String.valueOf(new Faker().number().numberBetween(100, 1000)))
					.replace("fname", new Faker().name().firstName())
					.replace("lname", new Faker().name().lastName())
					.replace("testmail@gmail.com", new Faker().ancient().god()+"@gmail.com");
		
		Response response = given()
				.header("Content-Type", ContentType.JSON)
				.log()
				.all()
				.body(latestRequestBody)
				.post("http://localhost:3000/employees");
		
		response.prettyPrint();
		
		System.out.println(response.getStatusCode());
		
		System.out.println(response.getTimeIn(TimeUnit.SECONDS));
				
	}
	
	// 4. using Map and List form java
	// a. {} --> use Map Interface
	// b. [] --> use Array list
	// cons - very verbose and 	not suitable for bigger json body
	
	
	@Test(enabled = false)
	public void postMethodWithRequestBodyUsingMapAndList() {
//		
//		{
//		      "id": 568,
//		      "firstname": "murali",
//		      "lastname": "krishnan",
//		      "email": "testermk@gmail.com"
//		      "jobs": ["manual qa", "automation tester"]'
//		      "favFoods": {
//					"breakFast":"idly",
//					"lunch": "briyani",
//					"dinner": ["chappathi", "milk"]
//		}
		
		Map<String, Object> requestBodyMap = new HashMap<>();
		
		requestBodyMap.put("id", new Faker().number().numberBetween(100, 1000));
		requestBodyMap.put("fname", new Faker().name().firstName());
		requestBodyMap.put("lname", new Faker().name().lastName());
		requestBodyMap.put("email", new Faker().artist().name().toString()+"@gmail.com");
		
		List<String> listOfJobs = new ArrayList<String>();
		listOfJobs.add("manual qa");
		listOfJobs.add("automation tester");
		
		requestBodyMap.put("jobs", listOfJobs);
		
		Map<String, Object> favFoodMap = new HashMap<>();
		favFoodMap.put("breakFast", new Faker().food().dish());
		favFoodMap.put("lunch", new Faker().food().sushi());
		
		List<String> listOfDinner = new ArrayList<>();
		listOfDinner.add(new Faker().food().fruit());
		listOfDinner.add(new Faker().food().spice());
		
		favFoodMap.put("dinner", listOfDinner);
		
		requestBodyMap.put("favFods", favFoodMap);
		
		
		Response response = given().header("Content-Type", ContentType.JSON)
				.log()
				.all()
				.body(requestBodyMap)
				.post("http://localhost:3000/employees");
		
		response.prettyPrint();
		
	}
	
	// 5. Using external JSON library
	// a. this library have some collections that solve the problems we had using map and list
	// {} --> JsonObject
	// [] --> JsonArray
	
	@Test
	public void postMethodUsingJSONLibrary() {
		
		JSONObject jsonRequestBody = new JSONObject();
		
		jsonRequestBody.put("id", new Faker().number().numberBetween(100, 1000));
		jsonRequestBody.put("fname", new Faker().name().firstName());
		jsonRequestBody.put("lname", new Faker().name().lastName());
		jsonRequestBody.put("email", new Faker().artist().name().toString()+"@gmail.com");
		
		//accumulate method creates a array of emails with same key and adds it.
		
		jsonRequestBody.accumulate("email", new Faker().animal().name().toString()+"@gmail.com");
		
		
		JSONArray listOfJobs = new JSONArray();
		
		listOfJobs.put("manual qa");
		listOfJobs.put("automation tester");
		
		jsonRequestBody.put("jobs", listOfJobs);
		
		JSONObject favFoods = new JSONObject();
		
		favFoods.put("breakFast", new Faker().food().dish());
		favFoods.put("lunch", new Faker().food().sushi());
		
		JSONArray listOfDinner = new JSONArray();
		
		listOfDinner.put(new Faker().food().fruit());
		listOfDinner.put(new Faker().food().spice());
		
		favFoods.put("dinner", listOfDinner);
		
		jsonRequestBody.put("favFods", favFoods);
		
		// The Jackson library only had the capability to serialize Java objects, 
		// but here, Our request is of JSONObject type
		
		Map<String, Object> jsonRequestMap = jsonRequestBody.toMap(); // this is java collection which Jackson library can serialize
		
		// we can also use .toString() also, in such case we do not need to serialize as well.
		
		// jsonRequestBody.toString();
		
		Response response = given().header("Content-Type", ContentType.JSON)
				.log()
				.all()
				.body(jsonRequestMap)
				.post("http://localhost:3000/employees");
		
		response.prettyPrint();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
