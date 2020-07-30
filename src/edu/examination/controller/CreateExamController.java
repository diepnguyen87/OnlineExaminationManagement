package edu.examination.controller;

import edu.examination.dao.AdminDao;
import edu.examination.dao.ExamDao;
import edu.examination.dao.InstituationDao;
import edu.examination.dao.OptionDao;
import edu.examination.dao.QuestionAnswerDao;
import edu.examination.dao.QuestionDao;
import edu.examination.dao.impl.AdminDaoImpl;
import edu.examination.dao.impl.ExamDaoImpl;
import edu.examination.dao.impl.InstituationDaoImpl;
import edu.examination.dao.impl.OptionDaoImpl;
import edu.examination.dao.impl.QuestionAnswerDaoImpl;
import edu.examination.dao.impl.QuestionDaoImpl;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionAnswerEntity;
import edu.examination.entity.QuestionEntity;
import edu.examination.view.LoginPage;

public class CreateExamController {

	protected ExamDao exam;
	protected QuestionDao question;
	protected OptionDao option;
	protected InstituationDao instituation;
	protected AdminDao admin;
	protected LoginPage loginPage;
	protected QuestionAnswerDao qAnswer;
	
	public String role;
	
	public CreateExamController(){
		instituation = new InstituationDaoImpl();
		exam = new ExamDaoImpl();
		question = new QuestionDaoImpl();
		option = new OptionDaoImpl();
	}
	
	public CreateExamController(LoginPage loginPage){
		this();
		this.loginPage = loginPage;
		role = loginPage.role;
		if(role.equals("Admin")){
			admin = new AdminDaoImpl();
		}else if(role.equals("Instituation")){
			instituation = new InstituationDaoImpl();
		}
	}
	
	public void createExam(ExamEntity newExam, String examAuthor){
		if(role.equals("Admin")){
			newExam.setExamAdminAuthor(admin.getAdminID(examAuthor));
		}else if(role.equals("Instituation")){
			newExam.setExamInstituationAuthor(instituation.getInstituationID(examAuthor));
		}
		exam.addExam(newExam);
	}
	
	public String getExamID(String examTitle){
		String examID = exam.getExamID(examTitle);
		return examID;
	}
	
	public void createQuestion(String examID, String questionText, int questionMark){
		question.addQuestion(new QuestionEntity(examID, questionText, questionMark));
	}
	
	public String getQuestionID(String examID, String questionText){
		String questionID = question.getQuestionID(examID, questionText);
		return questionID;
	}
	
	public void createOption(String questionID, int optionNumber, String optionText){
		option.addOption(new OptionEntity(questionID, optionNumber, optionText));
	}
	
	public boolean isExamTitleDuplicated(String examTitle){
		String result = exam.getExamID(examTitle);
		if(result == null){
			return false;
		}
		return true;
	}
	
	public void createQuestionAnswers(QuestionAnswerEntity questionAnswer){
		qAnswer = new QuestionAnswerDaoImpl();
		qAnswer.addQuestionAnswer(questionAnswer);
	}

}
