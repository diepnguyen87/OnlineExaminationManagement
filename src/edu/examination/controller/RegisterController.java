package edu.examination.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.examination.dao.InstituationDao;
import edu.examination.dao.impl.InstituationDaoImpl;
import edu.examination.entity.InstituationEntity;

public class RegisterController {

	private InstituationDao instituation;
	
	public RegisterController(){
		instituation = new InstituationDaoImpl();
	}
	
	public boolean register(String email, String password){
		InstituationEntity insti = new InstituationEntity(email, password);
		if(instituation.addInstituation(insti) == 1){
			return true;
		}
		return false;
	}
	
	public boolean isEmailDuplicated(String email){
		List<InstituationEntity> instituationList = instituation.getAllInstituations();
		for(InstituationEntity insti : instituationList){
			String currentInstiEmailAddress = insti.getInstiEmailAddress();
			if(currentInstiEmailAddress.equals(email)){
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
