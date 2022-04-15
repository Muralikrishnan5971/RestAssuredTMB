package com.pojo;

public class StudentBuilder {

	private String firstname;
	private String lastname;
	private int id;
	private String email;

	public StudentBuilder builder() {

		return this;
	}

	public Student build() {

		return new Student(this.firstname, this.lastname, this.id, this.email);
	}

	public StudentBuilder setFirstname(String firstname) {

		this.firstname = firstname;
		return this;
	}

	public StudentBuilder setLastname(String lastname) {

		this.lastname = lastname;
		return this;
	}

	public StudentBuilder setId(int id) {

		this.id = id;
		return this;
	}

	public StudentBuilder setEmail(String email) {

		this.email = email;
		return this;
	}
	
	// the below methods are syntactic sugar coats added to improve readability

	public StudentBuilder and() {

		return this;
	}

	public StudentBuilder then() {

		return this;
	}

}
