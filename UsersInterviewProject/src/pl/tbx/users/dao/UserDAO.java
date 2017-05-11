package pl.tbx.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.tbx.users.model.User;
import pl.tbx.users.model.Users;

public class UserDAO {

	private final static String DBURL = "jdbc:mysql://localhost:3306/users_database";
	private final static String DBUSER = "root";
	private final static String DBPASS = "kaliber";
	private final static String DBDRIVER = "com.mysql.jdbc.Driver";

	public int save(Users users) {
		try {
			String sql = "INSERT INTO users (name, surname, login) VALUES (?, ?, ?)";

			Class.forName(DBDRIVER).newInstance();
			Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			PreparedStatement ps = connection.prepareStatement(sql);
			
			for (User user : users.getUsers()) {
				ps.setString(1, user.getName());
				ps.setString(2, user.getSurname());
				ps.setString(3, user.getLogin());
				ps.addBatch();
			}
			
			ps.executeBatch();
			ps.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users.getUsers().size();
	}

	public Users getAll() {
		List<User> usersList = new ArrayList<>();

		try {
			Class.forName(DBDRIVER).newInstance();
			Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

			String sql = "SELECT * FROM users";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				usersList.add(new User(result.getString("name"), result
						.getString("surname"), result.getString("login")));
			}

			statement.close();
			connection.close();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Users(usersList);
	}
}
