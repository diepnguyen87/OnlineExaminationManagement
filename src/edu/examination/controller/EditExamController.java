package edu.examination.controller;

import java.util.ArrayList;
import java.util.List;

import edu.examination.dao.AdminDao;
import edu.examination.dao.ExamDao;
import edu.examination.dao.InstituationDao;
import edu.examination.dao.impl.AdminDaoImpl;
import edu.examination.dao.impl.ExamDaoImpl;
import edu.examination.dao.impl.InstituationDaoImpl;
import edu.examination.entity.ExamEntity;
import edu.examination.view.LoginPage;

public class EditExamController {

	protected ExamDao exam;
	protected AdminDao admin;
	protected InstituationDao instuation;
	protected LoginPage loginPage;
	public String role;
	
	public EditExamController(){
		exam = new ExamDaoImpl();
	}
	
	public EditExamController(LoginPage loginPage){
		this();
		this.loginPage = loginPage;
		this.role = loginPage.role;
		if(role.equals("Admin")){
			admin = new AdminDaoImpl();
		}else if(role.equals("Instituation")){
			instuation = new InstituationDaoImpl();
		}
	}
	
	public List<ExamEntity> displayDraftExams(String examAuthor){
		List<ExamEntity> draftExamsList = new ArrayList<ExamEntity>();
		if(role.equals("Admin")){
			draftExamsList = exam.getDraftExams_Admin(admin.getAdminID(examAuthor));
		}else if(role.equals("Instituation")){
			draftExamsList = exam.getDraftExams_Institute(instuation.getInstituationID(examAuthor));
		}
		return draftExamsList;
	}
	
	public ExamEntity getDraftExamByTitle(String examTitle){
		return exam.getExam(examTitle);
	}
	
	public boolean editExamTitle(String examTitle, String examID){
		int result = exam.updateExamTitle(examTitle, examID);
		if(result == 1){
			return true;
		}
		return false;
	}
	
	public boolean editExamDuration(int examDuration, String examID){
		int result = exam.updateExamDuration(examDuration, examID);
		if(result == 1){
			return true;
		}
		return false;
	}
	
}
