package com.MyTestRequests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.pojo.Employee;
import com.pojo.FavFoods;
import com.pojo.Marks;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostRequestUsingPojo {

	// POJO --> Plain Old Java Object
	// {} --> Class file
	// [] --> List<Type>
	
	
	@Test
	public void postRequestTestUsingPojo() {
		
		Marks sem1Marks = new Marks(72, 78);
		Marks sem2Marks = new Marks(94, 85);
		
		List<String> dinner = new ArrayList<String>();
		dinner.add("pulka");
		dinner.add("milk");
		
	//	Arrays.asList("pulka", "milk");
		
		FavFoods favFoods = new FavFoods("Idly", "Rice", dinner);
		
		Employee employee = new Employee(new Faker().number().numberBetween(100, 1000), 
				new Faker().name().firstName(), new Faker().name().lastName(), new Faker().artist().name().toString()+"@gmail.com",
				favFoods, Arrays.asList("manual qa", "automation tester"), Arrays.asList(sem1Marks, sem2Marks));
		
		
		Response response = given()
				.header("Content-Type", ContentType.JSON)
				.log()
				.all()
				.body(employee)
				.post("http://localhost:3000/employees");
		
		response.prettyPrint();
		
	    Assert.assertEquals(response.getStatusCode(), 201);   
	    Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	    
	    System.out.println(response.jsonPath().getString("email"));
	    
	    List<String> jobList = response.jsonPath().getList("jobs");
	    
	    System.out.println(jobList);
		
	    System.out.println(response.jsonPath().getString("favfoods.dinner[0]"));
	    
	    // deserialize our respose to our pojo classes and then play with them to validate
	    
	    Employee deserializedResponse = response.as(Employee.class);
	    
	    System.out.println(deserializedResponse.getFirstname());
	    
	    List<Marks> markLst = deserializedResponse.getMarks();
	    
	    for (Marks marks : markLst) {
			
	    	   System.out.println(marks.getEnglish());
	    	   System.out.println(marks.getTamil());
		}
	 
	    // Schema Validation
	    // classpath means -> src/main/resources or src/test/resources
	    
	    response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
	    
	    
	    
	    
	    
	}
}
