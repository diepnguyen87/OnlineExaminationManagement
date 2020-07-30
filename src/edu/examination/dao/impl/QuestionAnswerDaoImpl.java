package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.QuestionAnswerDao;
import edu.examination.entity.QuestionAnswerEntity;

public class QuestionAnswerDaoImpl implements QuestionAnswerDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public QuestionAnswerDaoImpl() {
		
	}
	
	private Connection getConnection(){
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public int addQuestionAnswer(QuestionAnswerEntity newQuestionAnswer) {
		int addedRows=0;
		try {
		String queryString = "Insert into question_answer(question_id, option_id) "
							+ "values(?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newQuestionAnswer.getQuestionID());
			ptmt.setString(2, newQuestionAnswer.getOptionID());
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
	public List<QuestionAnswerEntity> getQuestionAnswerByExamID(String examID) {
		List<QuestionAnswerEntity> questionAnswerList = new ArrayList<QuestionAnswerEntity>();
		try{
			String queryString = "SELECT d.question_id, d.option_id "
								+ "FROM exam c "
								+ "INNER JOIN "
									+ "(SELECT a.question_id, a.exam_id, a.question_mark, b.option_id "
									+ "FROM question a "
									+ "INNER JOIN question_answer b "
									+ "ON a.question_id = b.question_id) d "
								+ "ON c.exam_id = d.exam_id "
								+ "WHERE c.exam_id = ?";
			
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examID);
			resultSet = ptmt.executeQuery();
			while(resultSet.next()){
				QuestionAnswerEntity questionAnswer = new QuestionAnswerEntity();
				questionAnswer.setQuestionID(resultSet.getString("question_id"));
				questionAnswer.setQuestionID(resultSet.getString("option_id"));
				questionAnswerList.add(questionAnswer);
			}
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
		return questionAnswerList;
	}
}
