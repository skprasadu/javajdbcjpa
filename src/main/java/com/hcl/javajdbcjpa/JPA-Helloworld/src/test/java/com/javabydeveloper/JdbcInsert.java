package com.javabydeveloper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JdbcInsert {

	private static final String SQL = "INSERT INTO USER "
			+ "(ID, USER_NAME, PASSWORD, EMAIL, CREATED_TIME, UPDATED_TIME, DOB, USER_TYPE)"
			+ " VALUES (?,?,?,?,?,?,?,?)";

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jpa_jbd", "root", "");
				PreparedStatement ps = conn.prepareStatement(SQL)) {

			ps.setInt(1, 5);
			ps.setString(2, "Christian");
			ps.setString(3, "Christian");
			ps.setString(4, "Christian@gmail.com");
			ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			ps.setTimestamp(6, null);
			ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(8, "ADMIN");

			int row = ps.executeUpdate();
			System.out.println("Record has been inserted into the DataBase Table User");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
