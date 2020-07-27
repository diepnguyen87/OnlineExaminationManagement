package edu.examination.entity;

public class OptionEntity {

	private String optionID;
	private String questionID;
	private int optionNumber;
	private String optionText;
	
	public OptionEntity(){
		
	}
	
	public OptionEntity(int optionNumber, String optionText){
		super();
		this.optionNumber = optionNumber;
		this.optionText = optionText;
	}
	
	public OptionEntity(String questionID, int optionNumber, String optionText) {
		super();
		this.questionID = questionID;
		this.optionNumber = optionNumber;
		this.optionText = optionText;
	}

	public String getOptionID() {
		return optionID;
	}

	public void setOptionID(String optionID) {
		this.optionID = optionID;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public int getOptionNumber() {
		return optionNumber;
	}

	public void setOptionNumber(int optionNumber) {
		this.optionNumber = optionNumber;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	
}
