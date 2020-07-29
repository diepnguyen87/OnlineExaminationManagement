package edu.examination.controller;

import java.util.List;

import edu.examination.dao.ExamDao;
import edu.examination.dao.OptionDao;
import edu.examination.dao.QuestionDao;
import edu.examination.dao.UserAnswerDao;
import edu.examination.dao.UserDao;
import edu.examination.dao.impl.ExamDaoImpl;
import edu.examination.dao.impl.OptionDaoImpl;
import edu.examination.dao.impl.QuestionDaoImpl;
import edu.examination.dao.impl.UserAnswerDaoImpl;
import edu.examination.dao.impl.UserDaoImpl;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionEntity;
import edu.examination.entity.UserAnswerEntity;
import edu.examination.view.LoginPage;

public class UserController {

	protected ExamDao examD;
	protected UserDao userD;
	protected QuestionDao questionD;
	protected OptionDao optionD;
	protected UserAnswerDao userAnswerD;
	protected LoginPage loginPage;
	public String role;
	
	public UserController(){
		examD = new ExamDaoImpl();
		questionD = new QuestionDaoImpl();
		optionD = new OptionDaoImpl();
		userAnswerD = new UserAnswerDaoImpl();
		userD = new UserDaoImpl();
	}
	
	public UserController(LoginPage loginPage){
		this();
		this.loginPage = loginPage;
		this.role = loginPage.role;
	}
	
	public List<ExamEntity> getSubmittedExams(){
		List<ExamEntity> allSubmittedExams = examD.getSubmittedExams();
		return allSubmittedExams;
	}
	
	public ExamEntity getExamByTitle(String examTitle){
		return examD.getExam(examTitle);
	}
	
	public List<QuestionEntity> getAllQuestionsByExamID(String examID){
		List<QuestionEntity> questionList = questionD.getQuestionByExamID(examID);
		return questionList;
	}
	
	public List<OptionEntity> getAllOptionsByQuestionID(String questionID){
		List<OptionEntity> optionList = optionD.getOptionByQuestionID(questionID);
		return optionList;
	}
	
	public void insertUserAnswer(UserAnswerEntity uAnswer){
		userAnswerD.addUserAnswer(uAnswer);
	}
	
	public String getUserID(String userEmail){
		return userD.getUserByUserEmail(userEmail).getUserID();
	}
	
	public String getOptionID(String questionID, int optionNumber){
		return optionD.getOption(questionID, optionNumber).getOptionID();
	}
}
