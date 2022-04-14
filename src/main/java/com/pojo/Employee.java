package com.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)	// does not send the key with NULL value
 // does not send the key with EMPTY value
@JsonPropertyOrder(alphabetic = true)
public class Employee {

	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private FavFoods favfoods;
	private List<String> jobs;
	private List<Marks> marks;
	
	public Employee() {
		
	}

}
