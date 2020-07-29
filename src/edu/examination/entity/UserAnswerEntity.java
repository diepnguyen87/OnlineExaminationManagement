package edu.examination.entity;

public class UserAnswerEntity {

	private String userAnswerID;
	private String userID;
	private String examID;
	private String questionID;
	private String userOptionID;
	
	public String getUserAnswerID() {
		return userAnswerID;
	}

	public void setUserAnswerID(String userAnswerID) {
		this.userAnswerID = userAnswerID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getUserOptionID() {
		return userOptionID;
	}

	public void setUserOptionID(String userOptionID) {
		this.userOptionID = userOptionID;
	}

	public UserAnswerEntity(){
		
	}
	
	public UserAnswerEntity(String userID, String examID, String questionID, String userOptionID) {
		super();
		this.userID = userID;
		this.examID = examID;
		this.questionID = questionID;
		this.userOptionID = userOptionID;
	}
	
		
}
