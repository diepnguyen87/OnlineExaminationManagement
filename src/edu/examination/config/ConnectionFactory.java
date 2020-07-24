package edu.examination.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	String driverClassName = "org.mariadb.jdbc.Driver";
	String connectionUrl = "jdbc:mariadb://localhost:3306/exam_management";
	String dbUser = "root";
	String dbPwd = "123abc";
	
	private static ConnectionFactory connectionFactory = null;
	
	private ConnectionFactory(){
		try{
			Class.forName(driverClassName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}
	
	public static ConnectionFactory getInstance(){
		if(connectionFactory == null){
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
