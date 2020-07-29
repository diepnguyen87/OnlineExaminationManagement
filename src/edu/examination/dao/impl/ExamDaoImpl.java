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
	public ExamEntity getExam(String examTitle) {
		try{
			String queryString = "Select * from exam " +
								"Where exam_title = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examTitle);
			resultSet = ptmt.executeQuery();
			if(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamID(resultSet.getString("exam_id"));
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				currentExam.setIsDraft(resultSet.getString("is_draft"));
				return currentExam;
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
		return null;
	}

	@Override
	public int addExam(ExamEntity newExam) {
		int addedRows=0;
		try {
		String queryString = "Insert into exam(exam_title, exam_duration, total_question, exam_instituation_author, exam_admin_author, is_draft) "
							+ "values(?,?,?,?,?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, newExam.getExamTitle());
			ptmt.setInt(2, newExam.getExamDuration());
			ptmt.setInt(3, newExam.getTotalQuestion());
			ptmt.setString(4, newExam.getExamInstituationAuthor());
			ptmt.setString(5, newExam.getExamAdminAuthor());
			ptmt.setString(6, newExam.getIsDraft());
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
			if(resultSet.next()){
				return resultSet.getString("exam_id");
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

	@Override
	public List<ExamEntity> getSubmittedExams() {
		try{
			String queryString = "Select * from exam "
								+ "where is_draft = 'N'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			List<ExamEntity> examList = new ArrayList<ExamEntity>();
			int i = 0;
			while(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamID(resultSet.getString("exam_id"));
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				currentExam.setIsDraft(resultSet.getString("is_draft"));
				
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
	public List<ExamEntity> getSubmittedExams_Admin(String examAuthorID) {
		try{
			String queryString = "Select * from exam "
								+ "where exam_admin_author = ? "
								+ "and is_draft = 'N'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examAuthorID);
			resultSet = ptmt.executeQuery();
			List<ExamEntity> examList = new ArrayList<ExamEntity>();
			int i = 0;
			while(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamID(resultSet.getString("exam_id"));
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				currentExam.setIsDraft(resultSet.getString("is_draft"));
				
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
	public List<ExamEntity> getSubmittedExams_Institute(String examAuthorID) {
		try{
			String queryString = "Select * from exam "
								+ "where exam_instituation_author = ? "
								+ "and is_draft = 'N'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examAuthorID);
			resultSet = ptmt.executeQuery();
			List<ExamEntity> examList = new ArrayList<ExamEntity>();
			int i = 0;
			while(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamID(resultSet.getString("exam_id"));
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				currentExam.setIsDraft(resultSet.getString("is_draft"));
				
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
	public List<ExamEntity> getDraftExams_Admin(String examAuthorID) {
		try{
			String queryString = "Select * from exam "
								+ "where exam_admin_author = ? "
								+ "and is_draft = 'Y'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examAuthorID);
			resultSet = ptmt.executeQuery();
			List<ExamEntity> examList = new ArrayList<ExamEntity>();
			int i = 0;
			while(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamID(resultSet.getString("exam_id"));
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				currentExam.setIsDraft(resultSet.getString("is_draft"));
				
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
	public List<ExamEntity> getDraftExams_Institute(String examAuthorID) {
		try{
			String queryString = "Select * from exam "
								+ "where exam_instituation_author = ? "
								+ "and is_draft = 'Y'";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examAuthorID);
			resultSet = ptmt.executeQuery();
			List<ExamEntity> examList = new ArrayList<ExamEntity>();
			int i = 0;
			while(resultSet.next()){
				ExamEntity currentExam = new ExamEntity();
				currentExam.setExamID(resultSet.getString("exam_id"));
				currentExam.setExamTitle(resultSet.getString("exam_title"));
				currentExam.setExamDuration(resultSet.getInt("exam_duration"));
				currentExam.setTotalQuestion(resultSet.getInt("total_question"));
				currentExam.setExamInstituationAuthor(resultSet.getString("exam_instituation_author"));
				currentExam.setExamAdminAuthor(resultSet.getString("exam_admin_author"));
				currentExam.setCreatedOn(resultSet.getDate("created_on"));
				currentExam.setIsDraft(resultSet.getString("is_draft"));
				
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
	public int updateExamTitle(String examTitle, String examID) {
		int result = -1;
		try{
			String queryString = "Update exam "
								+ "set exam_title = ? "
								+ "Where exam_id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, examTitle);
			ptmt.setString(2, examID);
			result = ptmt.executeUpdate();
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
		return result;
	}

	@Override
	public int updateExamDuration(int examDuration, String examID) {
		int result = -1;
		try{
			String queryString = "Update exam "
								+ "set exam_duration = ? "
								+ "Where exam_id = ?";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setInt(1, examDuration);
			ptmt.setString(2, examID);
			result = ptmt.executeUpdate();
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
		return result;
	}	
}
