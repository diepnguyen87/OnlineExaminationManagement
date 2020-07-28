package edu.examination.entity;

import java.util.Date;

public class ExamEntity {

	private String examID;
	private String examTitle;
	private int examDuration;
	private int totalQuestion;
	private String examInstituationAuthor;
	private String examAdminAuthor;
	private Date createdOn;
	private String isDraft = "N";
	
	
	public ExamEntity(){
		
	}
	
	public ExamEntity(String examTitle, int examDuration, int totalQuestion,
			String examInstituationAuthor, String examAdminAuthor, String isDraft) {
		super();
		this.examTitle = examTitle;
		this.examDuration = examDuration;
		this.totalQuestion = totalQuestion;
		this.examInstituationAuthor = examInstituationAuthor;
		this.examAdminAuthor = examAdminAuthor;
		this.isDraft = isDraft;
	}
	
	public String getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(String isDraft) {
		this.isDraft = isDraft;
	}
	
	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}

	public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getExamInstituationAuthor() {
		return examInstituationAuthor;
	}

	public void setExamInstituationAuthor(String examInstituationAuthor) {
		this.examInstituationAuthor = examInstituationAuthor;
	}

	public String getExamAdminAuthor() {
		return examAdminAuthor;
	}

	public void setExamAdminAuthor(String examAdminAuthor) {
		this.examAdminAuthor = examAdminAuthor;
	}
	
}
