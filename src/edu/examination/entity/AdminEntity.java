package edu.examination.entity;

public class AdminEntity {

	private String adminID;
	private String adminEmailAddress;
	private String adminPassword;
	
	public AdminEntity(){
		
	}
	
	public AdminEntity(String adminEmailAddress, String adminPassword) {
		super();
		this.adminEmailAddress = adminEmailAddress;
		this.adminPassword = adminPassword;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminEmailAddress() {
		return adminEmailAddress;
	}

	public void setAdminEmailAddress(String adminEmailAddress) {
		this.adminEmailAddress = adminEmailAddress;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
