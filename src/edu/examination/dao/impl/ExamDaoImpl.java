package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.ExamDao;
import edu.examination.entity.ExamEntity;

public class ExamDaoImpl implements ExamDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public ExamDaoImpl() {
		
	}
	
	private Connection getConnection(){
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public List<ExamEntity> getAllExams() {
		try{
			String queryString = "Select * from exam";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			List<ExamEntity> examList = new ArrayList<ExamEntity>();
			int i = 0;
			while(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				examList.add(currentExam);
				i++;
			}
			return examList;
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
	public ExamEntity getExam(int examID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addExam(ExamEntity newExam) {
		int addedRows=0;
		try {
		String queryString = "Insert into exam(exam_title, exam_duration, total_question, exam_instituation_author, exam_admin_author) "
							+ "values(?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newExam.getExamTitle());
			ptmt.setInt(2, newExam.getExamDuration());
			ptmt.setInt(3, newExam.getTotalQuestion());
			ptmt.setString(4, newExam.getExamInstituationAuthor());
			ptmt.setString(5, newExam.getExamAdminAuthor());
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
	public String getExamID(String examTitle) {
		try{
			String queryString = "Select * from exam " +
								"Where exam_title = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examTitle);
			resultSet = ptmt.executeQuery();
			resultSet.next();
			return resultSet.getString("exam_id");
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
	public int updateTotalQuestion(String examID, int totalQuestion) {
		try{
			String queryString = "Update exam "
								+ "set total_question = ? "
								+ "where exam_id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, totalQuestion);
			ptmt.setString(2, examID);
			return ptmt.executeUpdate();
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
		return -1;
	}

	
}
