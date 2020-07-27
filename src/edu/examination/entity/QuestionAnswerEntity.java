package edu.examination.entity;

public class QuestionAnswerEntity {

	private String questionID;
	private String optionID;
	
	public QuestionAnswerEntity(){
		
	}
	
	public QuestionAnswerEntity(String questionID, String optionID) {
		super();
		this.questionID = questionID;
		this.optionID = optionID;
	}

	public String getQuestionID() {
		return questionID;
	}

	public String getOptionID() {
		return optionID;
	}
	
}
