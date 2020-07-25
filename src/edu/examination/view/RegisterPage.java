package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.config.Message;
import edu.examination.controller.RegisterController;

public class RegisterPage extends RegisterController {
	
	private Scanner scanner = new Scanner(System.in);
	private String instiEmailAddress;
	private String instiPassword;
	
	public RegisterPage(){
	}
	
	public void registerInstituation(){
		System.out.println("==============REGISTER PAGE==============");
		enterEmail();
		enterPassword();
		if(register(instiEmailAddress, instiPassword)== true){
			System.out.println(Message.REGISTER_SUCCESSFUL.getDescription());
			
		}
		//thieu case register=false
	}
	
	private void enterEmail(){
		while(true){
			System.out.print("Enter instituation email: ");
			instiEmailAddress = scanner.nextLine();
			if(isValidEmail(instiEmailAddress)==false){
				System.err.println(Error.EMAIL_NOT_VALID.getDescription());
				continue;
			}
			
			if(isEmailDuplicated(instiEmailAddress)==true){
				System.err.println(Error.EMAIL_EXISTED.getDescription());
				continue;
			}
			break;
		}
	}
	
	private void enterPassword(){
		while(true){
			System.out.print("Enter instituation password: ");
			instiPassword = scanner.nextLine();
			if(isValidPassword(instiPassword) == false){
				System.err.println(Error.PASSWORD_NOT_VALID.getDescription());
				continue;
			}
			break;
		}	
	}
	
	
	
}
