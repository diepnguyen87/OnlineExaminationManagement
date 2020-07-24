package edu.examination.view;

import java.util.Scanner;

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
			System.out.println("REGISTRATION SUCCESSFUL");
		}
		//thieu case register=false
	}
	
	private void enterEmail(){
		while(true){
			System.out.print("Enter instituation email: ");
			instiEmailAddress = scanner.nextLine();
			if(isValidEmail(instiEmailAddress)==false){
				System.out.println("THE EMAIL IS NOT VALID. PLEASE ENTER AGAIN!");
				continue;
			}
			
			if(isEmailDuplicated(instiEmailAddress)==true){
				System.out.println("THE EMAIL HAVE EXISTED IN THE SYSTEM. PLEASE ENTER OTHER.");
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
				System.out.println("THE PASSWORD IS NOT VALID. PASSWORD MUST BE AT LEAST 6 CHARACTERS LONG, ONE UPPERCASE, ONE LOWERCASE AND ONE NUMERIC CHARACTER.");
				continue;
			}
			break;
		}	
	}
	
	
	
}
