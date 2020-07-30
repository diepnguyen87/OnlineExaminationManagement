package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public List<OptionEntity> getOptionByQuestionID(String questionID) {
		try{
			String queryString = "Select * from option "
								+ "where question_id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, questionID);
			resultSet = ptmt.executeQuery();
			List<OptionEntity> optionList = new ArrayList<OptionEntity>();
			int i = 0;
			while(resultSet.next()){
				OptionEntity currentOption = new OptionEntity();
				currentOption.setOptionID(resultSet.getString("option_id"));
				currentOption.setQuestionID(resultSet.getString("question_id"));
				currentOption.setOptionNumber(resultSet.getInt("option_number"));
				currentOption.setOptionText(resultSet.getString("option_text"));
				optionList.add(currentOption);
				i++;
			}
			return optionList;
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

	@Override
	public OptionEntity getOption(String questionID, int optionNumber) {
		OptionEntity option = new OptionEntity();
		try {
		String queryString = "select * "
							+ "from option "
							+ "where question_id = ? and option_number = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, questionID);
			ptmt.setInt(2, optionNumber);
			resultSet = ptmt.executeQuery();
			
			if(resultSet.next()){
				option.setOptionID(resultSet.getString("option_id"));
				option.setQuestionID(resultSet.getString("question_id"));
				option.setOptionNumber(resultSet.getInt("option_number"));
				option.setOptionText(resultSet.getString("option_text"));
			}
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
		return option;
	}

}
