package com.hcl.javajdbcjpa.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * What is Hibernate? ORM (Object Relational mapping) tool, that allows to Object oriented related 
 * models to database tables
 * How it is different from JDBC framework? In JDBC we need to write SQL langage. In Hibernate we need to 
 * do annotation to query database. In JDBC SQL langauge is written explicitly, where as in Hiberate 
 * for querying the database, we use HQL which works with Entities instead of directly working with Database
 * queries
 * What are the basic methods of Hibernate to store and retrieve information from Database? For storing the 
 * object in to the database we use persist method 
 * and for retrieving the objects from database, we use HQL and createQuery/ getResultList
 * How you convert a POJO to a Hibernate aware object? We use annotation @Entity to mark a POJO as Hibernate 
 * aware
 * What is the other most important Annotation of a Hibernate aware object? @Id is another important 
 * annotation, that indicates the primary key in the database table
 * How do you map an Entity to a database table? @Table is used to map, database table name to POJO or entity
 * How do you map the fields to column names? @Column is used to map database column name to POJO property 
 * or field
 * What is HQL (Hibernate Query Language) and how it is different from SQL(Structured Query Language)? 
 * HQL (only applicable to java application) operates on Entities and 
 * SQL operates on Tables (syntax are different). HQL/Hibernate is database independent/ vendor independant.
 * HQL underlying generates vendor specific SQL using vendor specific Dialect
 */
public class JpaApp {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("jbd-pu");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();

			Student student = new Student();
			student.setFirstName("Sumit");
			student.setLastName("Kausik");
			student.setContactNo("+1-701-298-2986");

			entityManager.persist(student);
			
			student = new Student();
			student.setFirstName("Krishna");
			student.setLastName("Prasad");
			student.setContactNo("+1-408-298-2986");
			entityManager.persist(student);

			transaction.commit();
/*
 * 'select s from Stu s' looks like SQL, but it is called HQL
 * Actual SQL statement likes like 'select * from Student'
 */
			Query q = entityManager.createQuery("select s from Stu s");

			List<Student> resultList = q.getResultList();
			System.out.println("num of sudents:" + resultList.size());
			for (Student next : resultList) {
				System.out.println("next student: " + next);
			}

		} catch (Exception e) {
			System.out.println(e);
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			if(entityManager != null) {
				entityManager.close();
			}
			if(emf != null) {
				emf.close();
			}
		}
	}
}
