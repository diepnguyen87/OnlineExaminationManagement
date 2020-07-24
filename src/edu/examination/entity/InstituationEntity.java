package edu.examination.entity;

import java.io.Serializable;

public class InstituationEntity implements Serializable {

	private String instituationID;
	private String instiEmailAddress;
	private String instiPassword;
	
	public InstituationEntity(){
		
	}
	
	public InstituationEntity(String instiEmailAddress, String instiPassword) {
		super();
		this.instiEmailAddress = instiEmailAddress;
		this.instiPassword = instiPassword;
	}
	
	public String getInstituationID() {
		return instituationID;
	}
	
	public void setInstituationID(String instituationID) {
		this.instituationID = instituationID;
	}
	
	public String getInstiEmailAddress() {
		return instiEmailAddress;
	}
	
	public void setInstiEmailAddress(String instiEmailAddress) {
		this.instiEmailAddress = instiEmailAddress;
	}
	
	public String getInstiPassword() {
		return instiPassword;
	}
	
	public void setInstiPassword(String instiPassword) {
		this.instiPassword = instiPassword;
	}
	
	
}
