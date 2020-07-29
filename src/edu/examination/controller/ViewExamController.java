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

public class ViewExamController {

	protected ExamDao exam;
	protected AdminDao admin;
	protected InstituationDao instuation;
	protected LoginPage loginPage;
	public String role;
	
	public ViewExamController(){
		exam = new ExamDaoImpl();
	}
	
	public ViewExamController(LoginPage loginPage){
		this();
		this.loginPage = loginPage;
		this.role = loginPage.role;
		if(role.equals("Admin")){
			admin = new AdminDaoImpl();
		}else if(role.equals("Instituation")){
			instuation = new InstituationDaoImpl();
		}
	}
	
	public List<ExamEntity> displaySubmittedExams(String examAuthor){
		List<ExamEntity> submittedExamsList = new ArrayList<ExamEntity>();
		if(role.equals("Admin")){
			submittedExamsList = exam.getSubmittedExams_Admin(admin.getAdminID(examAuthor));
		}else if(role.equals("Instituation")){
			submittedExamsList = exam.getSubmittedExams_Institute(instuation.getInstituationID(examAuthor));
		}
		return submittedExamsList;
	}
}
