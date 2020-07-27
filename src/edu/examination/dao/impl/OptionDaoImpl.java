package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.OptionDao;
import edu.examination.entity.OptionEntity;

public class OptionDaoImpl implements OptionDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public OptionDaoImpl() {
		
	}
	
	private Connection getConnection(){
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public List<OptionEntity> getAllOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionEntity getOption(int optionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addOption(OptionEntity newOption) {
		int addedRows=0;
		try {
		String queryString = "Insert into option(question_id, option_number, option_text) "
							+ "values(?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newOption.getQuestionID());
			ptmt.setInt(2, newOption.getOptionNumber());
			ptmt.setString(3, newOption.getOptionText());
			addedRows = ptmt.executeUpdate();
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
		return addedRows;
	}

}
