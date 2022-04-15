package com.MyBuilderPatternTest;

import org.testng.annotations.Test;

import com.pojo.Student;
import com.pojo.StudentBuilder;

public class BuilderDesignPatternTest {

	// 1. When no. of parameters increase, readability decrease
	// 2. In case we want to ignore some fields, then it becomes difficult to create
	// multiple constructors
	// Builder Design Pattern solves the above problem without compromising
	// Immutability

	// There are multiple ways to Builder Design Patter
	// 1. Using External Builder Class

	@Test
	public void builderTest() {

		// we can ignore some parameters during runtime. thats the beauty of Builder Design Pattern
		
		Student student = new StudentBuilder().builder()
				.setFirstname("Arthur")
				.and()
				.setId(999)
				.and()
				.setEmail("ilovefish@gmail.com")
				.then().build();
		
		System.out.println(student);
		
		// 2. Using a Static Inner Class
//		
//		Student student01 = new Student.StudentBuilderInnerClass().builder()
//				.setFirstname("Martha").setId(457).setLastname("wayne").build();
//		
//		System.out.println(student01);
		
		// 3. after adding lombok @Builder annotation
		
		Student student001 = Student.builder()
				.setFirstname("Murali").setId(425).setLastname("Krishnan").setEmail("tester@gmail.com").build();
		
		System.out.println(student001);
	}
	
	
	
	
}
