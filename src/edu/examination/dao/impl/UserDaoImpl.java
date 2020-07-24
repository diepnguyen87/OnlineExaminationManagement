package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.UserDao;
import edu.examination.entity.UserEntity;

public class UserDaoImpl implements UserDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public UserDaoImpl() {
		
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public List<UserEntity> getAllUsers() {
		try{
			String queryString = "Select * from user";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			List<UserEntity> userList = new ArrayList<UserEntity>();
			int i = 0;
			while(resultSet.next()){
				UserEntity currentUser = new UserEntity();
				currentUser.setUserEmailAddress(resultSet.getString("user_email_address"));
				currentUser.setUserPassword(resultSet.getString("user_password"));
				userList.add(currentUser);
				i++;
			}
			return userList;
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(resultSet != null){
					resultSet.close();
				}
				if(ptmt != null){
					ptmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	@Override
	public UserEntity getUser(int userID) {
		
		return null;
	}
	

	@Override
	public void addUser(UserEntity newUser) {
		try {
		String queryString = "Insert into user(userEmailAddress, userPassword) "
							+ "values(?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newUser.getUserEmailAddress());
			ptmt.setString(2, newUser.getUserPassword());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(ptmt != null){
					ptmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

}
