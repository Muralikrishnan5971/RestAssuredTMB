package com.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@JsonInclude(value = Include.NON_NULL)
@Builder(setterPrefix = "set")
public class Student {

	private String firstname;
	private String lastname;
	private int id;
	private String email;

	
	// Static Inner Class
//	
//	public static class StudentBuilderInnerClass {
//	
//		private String firstname;
//		private String lastname;
//		private int id;
//		private String email;
//		
//		public static StudentBuilderInnerClass builder() {
//			
//			return new StudentBuilderInnerClass();
//		}
//		
//		public StudentBuilderInnerClass setFirstname(String firstname) {
//
//			this.firstname = firstname;
//			return this;
//		}
//
//		public StudentBuilderInnerClass setLastname(String lastname) {
//
//			this.lastname = lastname;
//			return this;
//		}
//
//		public StudentBuilderInnerClass setId(int id) {
//
//			this.id = id;
//			return this;
//		}
//
//		public StudentBuilderInnerClass setEmail(String email) {
//
//			this.email = email;
//			return this;
//		}
//		
//		public Student build() {
//
//			return new Student(this.firstname, this.lastname, this.id, this.email);
//		}
//		
//	}
}
