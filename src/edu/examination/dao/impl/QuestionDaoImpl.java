package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.QuestionDao;
import edu.examination.entity.QuestionEntity;

public class QuestionDaoImpl implements QuestionDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public QuestionDaoImpl() {
		
	}
	
	private Connection getConnection(){
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public List<QuestionEntity> getAllQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionEntity getQuestion(int questionID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addQuestion(QuestionEntity newQuestion) {
		int addedRows=0;
		try {
		String queryString = "Insert into question(exam_id, question_text, question_mark) "
							+ "values(?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newQuestion.getExamID());
			ptmt.setString(2, newQuestion.getQuestionText());
			ptmt.setInt(3, newQuestion.getQuestionMark());
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
	public String getQuestionID(String examID, String questionText) {
		try{
			String queryString = "Select * from question " +
								"where exam_id = ? and question_text = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examID);
			ptmt.setString(2, questionText);
			resultSet = ptmt.executeQuery();
			resultSet.next();
			return resultSet.getString("question_id");
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

}
