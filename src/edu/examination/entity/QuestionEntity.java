package edu.examination.entity;

public class QuestionEntity {

	private String questionID;
	private String examID;
	private String questionText;
	private int questionMark;
	
	public QuestionEntity(){
		
	}
	
	public QuestionEntity(String examID, String questionText, int questionMark) {
		super();
		this.examID = examID;
		this.questionText = questionText;
		this.questionMark = questionMark;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getExamID() {
		return examID;
	}

	public void setExamID(String examID) {
		this.examID = examID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getQuestionMark() {
		return questionMark;
	}

	public void setQuestionMark(int questionMark) {
		this.questionMark = questionMark;
	}
	
	
	
}
