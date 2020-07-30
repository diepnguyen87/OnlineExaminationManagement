package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.UserAnswerDao;
import edu.examination.entity.UserAnswerEntity;

public class UserAnswerDaoImpl implements UserAnswerDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public UserAnswerDaoImpl() {
		
	}
	
	private Connection getConnection(){
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public int addUserAnswer(UserAnswerEntity userAnswer) {
		int addedRows=0;
		try {
		String queryString = "Insert into user_exam_question_answer(user_id, exam_id, question_id, user_answer_option) "
							+ "values(?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, userAnswer.getUserID());
			ptmt.setString(2, userAnswer.getExamID());
			ptmt.setString(3, userAnswer.getQuestionID());
			ptmt.setString(4, userAnswer.getUserOptionID());
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
	public List<UserAnswerEntity> getUserAnswerByExamID(String userID, String examID) {
		List<UserAnswerEntity> userAnswerList = new ArrayList<UserAnswerEntity>();
		
		try {
		String queryString = "select * "
							+ "from user_exam_question_answer "
							+ "where user_id = ? and exam_id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, userID);
			ptmt.setString(2, examID);
			resultSet = ptmt.executeQuery();
			
			while(resultSet.next()){
				UserAnswerEntity userAnswer = new UserAnswerEntity();
				userAnswer.setUserAnswerID(resultSet.getString("user_exam_question_answer_id"));
				userAnswer.setUserAnswerID(resultSet.getString("user_id"));
				userAnswer.setUserAnswerID(resultSet.getString("exam_id"));
				userAnswer.setUserAnswerID(resultSet.getString("question_id"));
				userAnswer.setUserAnswerID(resultSet.getString("user_answer_option"));
				userAnswerList.add(userAnswer);
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
		return userAnswerList;
	}
}
