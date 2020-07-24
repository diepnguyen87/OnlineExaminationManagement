package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.AdminDao;
import edu.examination.entity.AdminEntity;

public class AdminDaoImp implements AdminDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public AdminDaoImp(){
		
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public List<AdminEntity> getAllAdmins() {
		try{
			String queryString = "Select * from admin";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			List<AdminEntity> adminList = new ArrayList<AdminEntity>();
			int i = 0;
			while(resultSet.next()){
				AdminEntity currentAdmin = new AdminEntity();
				currentAdmin.setAdminEmailAddress(resultSet.getString("admin_email_address"));
				currentAdmin.setAdminPassword(resultSet.getString("admin_password"));
				adminList.add(currentAdmin);
				i++;
			}
			return adminList;
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
	public AdminEntity getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
