package com.hcl.javajdbcjpa.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Plain Old Java Object - aka POJO
//Class that has just some fields and it has getters and setters operating on it
//IT HAS NO BUSINESS LOGIC, this is equaliant of struct in c or c++ 
// and it is also called BEAN in Java
@Entity//(name = "Use")
public class User2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	private String email;
	
	private String country;

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
	
	@Override
	public String toString() {
		return "User [id=" + id + ", Name=" + name + ", Email=" + email
				+ ", Country=" + country + "]";
	}
}