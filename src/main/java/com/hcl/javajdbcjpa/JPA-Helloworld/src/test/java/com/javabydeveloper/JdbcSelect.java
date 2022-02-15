package com.javabydeveloper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcSelect {

	private static final String SQL = "SELECT * FROM jpa_jbd.user";

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jpa_jbd?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
				PreparedStatement ps = conn.prepareStatement(SQL)) {
			
			ResultSet rs = ps.executeQuery();
			
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				
				User user = new User();
				user.setId(rs.getLong("ID"));
				user.setUsername(rs.getString("USER_NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setEmail(rs.getString("EMAIL"));
				user.setCreationTime(rs.getDate("CREATED_TIME"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setDateofBirth(rs.getDate("DOB"));
				
				users.add(user);
			}

			// rows inserted
			System.out.println(users); 
			System.out.println("users list size is==>" +users.size()); 

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
