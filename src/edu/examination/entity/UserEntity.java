package edu.examination.entity;

public class UserEntity{

	private String userID;
	private String userEmailAddress;
	private String userPassword;
	
	public UserEntity(){
		
	}
	
	public UserEntity(String userEmailAddress, String userPassword) {
		super();
		this.userEmailAddress = userEmailAddress;
		this.userPassword = userPassword;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserEmailAddress() {
		return userEmailAddress;
	}
	
	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
