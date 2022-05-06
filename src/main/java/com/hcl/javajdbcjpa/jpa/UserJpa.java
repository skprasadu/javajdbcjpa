package com.hcl.javajdbcjpa.jpa;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hcl.javajdbcjpa.jdbc.User;

public class UserJpa {
	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;

		try {
			emf = Persistence.createEntityManagerFactory("jbd-pu");
			entityManager = emf.createEntityManager();
			transaction = entityManager.getTransaction();
			
			try (Scanner input = new Scanner(System.in);) {
				// main menu
				boolean menu = true;
				//while loop here
				while (menu) {
					System.out.println("What would you like to do: Insert, Update, Delete, Read, ReadAll, Quit?");
					String option = input.nextLine();
					switch(option.toLowerCase()) {
					case "insert":
						transaction.begin();
						User2 userI = createUser(input);
						entityManager.persist(userI);
						transaction.commit();
						break;
					case "update":	
						transaction.begin();
						int idU = inputID(input);
						User2 userU = entityManager.find(User2.class, idU);
						userU = updateUser(input, userU);
						transaction.commit();
						break;
					case "delete":
						transaction.begin();
						int idD = inputID(input);
						Query del = entityManager.createQuery("delete from User2 s where s.id=?0");
						del.setParameter(0, idD);
						del.executeUpdate();
						transaction.commit();
						break;
					case "read":
						int idR = inputID(input);
						Query read = entityManager.createQuery("select s from User2 s where s.id=?0");
						read.setParameter(0, idR);
						List<User2> resultList = read.getResultList();
						System.out.println("num of users:" + resultList.size());
						for (User2 next : resultList) {
							System.out.println("next user: " + next);
						}
						break;
					case "readall":
						Query readAll = entityManager.createQuery("select s from User2 s");

						List<User2> resultListAll = readAll.getResultList();
						System.out.println("num of sudents:" + resultListAll.size());
						for (User2 next : resultListAll) {
							System.out.println("next student: " + next);
						}
						break;
					case "quit":
						
						break;
					default:
						System.out.println("Invalid option");
						break;
					}
					menu =	menuOperator(input, menu, "Would you like to do anything else? ");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}
	
	private static int inputID(Scanner input) {
		System.out.println("Enter the ID");
		int id = input.nextInt();
		return id;
	}

	public static User2 createUser(Scanner input) {
		System.out.println("Enter the UserName");
		String username = input.nextLine();
		System.out.println("Enter the Email");
		String email = input.nextLine();
		System.out.println("Enter the location");
		String location = input.nextLine();
		
		User2 user = new User2();
		user.setName(username);
		user.setEmail(email);
		user.setCountry(location);
		
		return user; 
		
	}
	
	public static User2 updateUser(Scanner input, User2 user) {
		System.out.println("Enter the UserName");
		String username = input.nextLine();
		System.out.println("Enter the Email");
		String email = input.nextLine();
		System.out.println("Enter the location");
		String location = input.nextLine();
		
		user.setName(username);
		user.setEmail(email);
		user.setCountry(location);
		
		return user; 
		
	}
	
	private static boolean menuOperator(Scanner input, boolean go, String message) {
		// loops operation menu
		System.out.println(message);
		boolean gogo = true;
		while (gogo) {
			String repeat = input.nextLine();
			if (repeat.toUpperCase().equals("Y")) {
				gogo = false;
			} else if (repeat.toUpperCase().equals("N")) {
				go = false;
				gogo = false;
			} else {
				System.out.println("invalid input, try again. input Y or N: ");
			}
		}
		return go;
	}

}