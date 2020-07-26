package edu.examination.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.examination.dao.InstituationDao;
import edu.examination.dao.UserDao;
import edu.examination.dao.impl.InstituationDaoImpl;
import edu.examination.dao.impl.UserDaoImpl;
import edu.examination.entity.InstituationEntity;
import edu.examination.entity.UserEntity;

public class RegisterController {

	private InstituationDao instituation;
	private UserDao user;
	protected String role;
	
	public RegisterController() {
		
	}
	
	public RegisterController(String role){
		this.role = role;
		if(role.equals("Instituation")){
			instituation = new InstituationDaoImpl();
		}else if(role.equals("User")){
			user = new UserDaoImpl();
		}
	}
	
	
	public boolean register(String email, String password){
		boolean succesfulRegistration = false;
		
		if(role.equals("Instituation")){
			succesfulRegistration = registerInstituation(email, password);
		}else if(role.equals("User")){
			succesfulRegistration = registerUser(email, password);
		}
		return succesfulRegistration;
	}
	
	private boolean registerInstituation(String email, String password) {
		InstituationEntity insti = new InstituationEntity(email, password);
		if(instituation.addInstituation(insti) == 1){
			return true;
		}
		return false;
	}
	
	private boolean registerUser(String email, String password) {
		UserEntity newUser = new UserEntity(email, password);
		if(user.addUser(newUser) == 1){
			return true;
		}
		return false;
	}
	
	public boolean isEmailDuplicated(String email){
		boolean isDuplicated = false;
		
		if(role.equals("Instituation")){
			isDuplicated = isInstituationEmailDuplicated(email);
		}else if(role.equals("User")){
			isDuplicated = isUserEmailDuplicated(email);
		}
		return isDuplicated;
	}
	
	private boolean isInstituationEmailDuplicated(String email) {
		List<InstituationEntity> instituationList = instituation.getAllInstituations();
		for(InstituationEntity insti : instituationList){
			String currentInstiEmailAddress = insti.getInstiEmailAddress();
			if(currentInstiEmailAddress.equals(email)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isUserEmailDuplicated(String email) {
		List<UserEntity> userList = user.getAllUsers();
		for(UserEntity user : userList){
			String currentUserEmailAddress = user.getUserEmailAddress();
			if(currentUserEmailAddress.equals(email)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isValidEmail(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	}
	
	 public boolean isValidPassword(String password){ 	  
      // Regex to check valid password. 
      String regex = "^(?=.*[0-9])"
                     + "(?=.*[a-z])(?=.*[A-Z])"
                     + "(?=.*[@#$%^&+=?])"
                     + "(?=\\S+$).{6,20}$"; 

      // Compile the ReGex 
      Pattern p = Pattern.compile(regex); 

      // If the password is empty 
      // return false 
      if (password == null) { 
          return false; 
      } 

      // Pattern class contains matcher() method 
      // to find matching between given password 
      // and regular expression. 
      Matcher m = p.matcher(password); 

      // Return if the password 
      // matched the ReGex 
      return m.matches(); 
  } 
	
}
