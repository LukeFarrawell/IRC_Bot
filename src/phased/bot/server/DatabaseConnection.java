package phased.bot.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DatabaseConnection {
	
	private static final String SERVER_IP = "jdbc:mysql://localhost/bot";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private Connection connection;

	public DatabaseConnection() {
		try {
			connection = DriverManager.getConnection(SERVER_IP, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Unable to connect to the server!");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public ResultSet query(String query) {
		ResultSet sqlResult = null; 
		try {
			Statement satement = connection.createStatement();
			sqlResult = satement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sqlResult;
	}
	
	public void insert(String query) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(String query) {
		try {
			System.out.println(">>"+query);
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
