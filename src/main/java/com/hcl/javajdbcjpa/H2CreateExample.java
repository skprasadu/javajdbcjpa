package com.hcl.javajdbcjpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create Statement JDBC Example
 * 
 * @author Ramesh Fadatare
 *
 */
public class H2CreateExample {

	private static final String createTableSQL = "create table users (" + "  id  int primary key,"
			+ "  name varchar(20)," + "  email varchar(20)," + "  country varchar(20)," + "  password varchar(20) ) ";

	private static final String INSERT_USERS_SQL = "INSERT INTO users"
			+ "  (id, name, email, country, password) VALUES " + " (?, ?, ?, ?, ?);";

	private static final String QUERY = "select id,name,email,country,password from users where id =?";

	public static void main(String[] argv) throws SQLException {
		H2CreateExample createTableExample = new H2CreateExample();

		try (Connection connection = H2JDBCUtils.getConnection()) {
			createTableExample.createTable(connection);
			createTableExample.insertRecord(connection);
			createTableExample.selectRecord(connection);
		} catch (SQLException e) {
			// print SQL exception information
			H2JDBCUtils.printSQLException(e);
		}

	}

	public void createTable(Connection connection) throws SQLException {

		System.out.println(createTableSQL);
		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			Statement statement = connection.createStatement();
			{

				// Step 3: Execute the query or update query
				statement.execute(createTableSQL);
			}
		} catch (SQLException e) {
			// print SQL exception information
			H2JDBCUtils.printSQLException(e);
		}
	}

	// information
	public void insertRecord(Connection connection) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// Step 1: Establishing a Connection
		try {
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "Tony");
			preparedStatement.setString(3, "tony@gmail.com");
			preparedStatement.setString(4, "US");
			preparedStatement.setString(5, "secret");

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			// print SQL exception information
			H2JDBCUtils.printSQLException(e);
		}

		// Step 4: try-with-resource statement will auto close the connection.
	}

	public void selectRecord(Connection connection) throws SQLException {
		try {

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
			preparedStatement.setInt(1, 1);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String password = rs.getString("password");
				System.out.println(id + "," + name + "," + email + "," + country + "," + password);
			}
		} catch (SQLException e) {
			H2JDBCUtils.printSQLException(e);
		}
	}
}
