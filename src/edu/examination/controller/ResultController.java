package edu.examination.controller;

import java.util.List;

import edu.examination.dao.OptionDao;
import edu.examination.dao.QuestionAnswerDao;
import edu.examination.dao.QuestionDao;
import edu.examination.dao.UserAnswerDao;
import edu.examination.dao.UserDao;
import edu.examination.dao.impl.OptionDaoImpl;
import edu.examination.dao.impl.QuestionAnswerDaoImpl;
import edu.examination.dao.impl.UserAnswerDaoImpl;
import edu.examination.dao.impl.UserDaoImpl;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionAnswerEntity;
import edu.examination.entity.QuestionEntity;
import edu.examination.entity.UserAnswerEntity;
import edu.examination.view.LoginPage;

public class ResultController {

	private LoginPage loginPage;
	private ExamEntity exam;
	protected UserAnswerDao userAnswerD;
	protected UserDao userD;
	protected QuestionAnswerDao questionAnswersD;
	protected QuestionDao questionD;
	protected OptionDao optionD;
	
	public ResultController(){
		userAnswerD = new UserAnswerDaoImpl();
		userD = new UserDaoImpl();
		questionAnswersD = new QuestionAnswerDaoImpl();
		optionD = new OptionDaoImpl();
	}
	
	public ResultController(LoginPage loginPage, ExamEntity exam){
		this();
		this.loginPage = loginPage;
		this.exam = exam;
	}
	
	public List<UserAnswerEntity> getUserAnswersByExamID(){
		String userID = userD.getUserByUserEmail(loginPage.getEmail()).getUserID();
		String examID = exam.getExamID();
		return userAnswerD.getUserAnswerByExamID(userID, examID);
	}
	
	public List<QuestionAnswerEntity> getQuestionAnswers(){
		return questionAnswersD.getQuestionAnswerByExamID(exam.getExamID());
	}
	
	public List<QuestionEntity> getQuestionListByExamID(){
		return questionD.getQuestionByExamID(exam.getExamID());
	}
	
	public List<OptionEntity> getOptionListByQuestionID(String questionID){
		return optionD.getOptionByQuestionID(questionID);
	}
	
	public String getOptionTextByOptionID(List<OptionEntity> optionList, String optionID){
		for(OptionEntity op : optionList){
			if(op.getOptionID().equals(optionID)){
				return op.getOptionText();
			}
		}
		return null;
	}
	
}
