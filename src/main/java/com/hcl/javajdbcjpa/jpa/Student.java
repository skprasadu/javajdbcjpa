package com.hcl.javajdbcjpa.jpa;

import javax.persistence.Column; //JPA -> Java Persistence API
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * This POJO is Annotated with @Entity, that means this POJO knows how to persist and read from the database
 * CRUD operation. We dont need to write any SQL query to store or retrieve information from Database
 * this is mandatory
 */
@Entity(name = "Stu")
@Table(name="Student")
public class Student /* CamelCase */ {

	/*
	 * studentID is primary key of the database, because it is annotated with @Id annotation
	 * this is mandatory
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long studentId;

	@Column(name = "FNAME")
	private String firstName;

	@Column(name = "LNAME")
	private String lastName;

	@Column(name = "CONTACT_NO") //snake_case
	private String contactNo; // pascalCase

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}


	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactNo=" + contactNo + "]";
	}
	
}
