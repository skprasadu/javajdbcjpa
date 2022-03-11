package com.hcl.javajdbcjpa.jdbc;

//Plain Old Java Object - aka POJO
//Class that has just some fields and it has getters and setters operating on it
//IT HAS NO BUSINESS LOGIC, this is equaliant of struct in c or c++ 
// and it is also called BEAN in Java
public class User {
	
	private int id;
	private String name;
	private String email;
	private String country;
	
	public User(int id, String name, String email, String country) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
