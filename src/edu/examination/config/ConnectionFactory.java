package edu.examination.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

public class ConnectionFactory {
	String driverClassName = "org.mariadb.jdbc.Driver";
	String connectionUrl = "jdbc:mariadb://localhost:3306/exam_management";
	String dbUser = "root";
	String dbPwd = "123abc";
	
	private static ConnectionFactory connectionFactory = null;
	
	private ConnectionFactory(){
		try{
			Class.forName(driverClassName);
		}catch(ClassNotFoundException e){
			System.out.println(ConsoleColors.RED + "Driver not found: " + e.getMessage() + ConsoleColors.RESET);
			System.out.println(Message.CONTACT_ADMIN.getDescription());
			System.exit(0);
		}
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		}catch (SQLNonTransientConnectionException e) {
			System.out.println(ConsoleColors.RED + e.getMessage() + ConsoleColors.RESET);
			System.out.println(Message.CONTACT_ADMIN.getDescription());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(conn == null) {
			System.exit(0);
		}
		return conn;
	}
	
	public static ConnectionFactory getInstance(){
		if(connectionFactory == null){
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
