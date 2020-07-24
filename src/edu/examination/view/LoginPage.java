package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.controller.LoginController;


public class LoginPage extends LoginController {

	private Scanner scanner = new Scanner(System.in);
	private String email;
	private String password;
	
	public LoginPage(){
		
	}
	
	public LoginPage(String roleOption){
		if(roleOption.equals("1")){
			role = "Admin";
		}else if(roleOption.equals("2")){
			role = "Instituation";
		}else if(roleOption.equals("3")){
			role = "User";
		}
	}
	
	public void displayAdminLoginPage(){
		System.out.println("==============LOGIN PAGE==============");
		login();
	}
	
	public void displayLoginPage() {
		System.out.println("==============LOGIN PAGE==============");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");

		outerloop: while (true) {
			System.out.print("Select option (enter 1 or 2 or 3): ");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				login();
				break outerloop;
			case "2":
				RegisterPage register = new RegisterPage();
				register.registerInstituation();
				break outerloop;
			case "3":
				System.out.println("THANK FOR USING MY APP. BYE AND SEE YOU AGAIN!");
				System.exit(0);
			default:
				System.out.println("WARNING: INCORRECT OPTION. PLEASE ENTER AGAIN!");
			}
		}
	}

	public void login() {
		outerloop:
		while (true) {
			enterEmail();
			enterPassword();
			if (login(email, password) == true) {
				System.out.println("LOGIN SUCCESSFUL!");
				System.out.printf("WELCOME %s TO ONLINE EXAMINATION MANAGEMENT SYSTEM", email.toUpperCase());
				return;
			} else {
				System.out.println(Error.INCORRECT_AUTHENICATION.getDescription());
				System.out.print("TRY AGAIN? ENTER (Y/N): ");
				String option = scanner.nextLine().toUpperCase();
				
				switch (option) {
				case "N":
					break outerloop;
				default:
					continue;
				}
			}
		}
		if(role.equals("Admin")){
			displayAdminLoginPage();
		}else{
			displayLoginPage();
		}
	}

	private void enterEmail() {
		while (true) {
			System.out.print("Enter email: ");
			email = scanner.nextLine();
			if (email.isEmpty()) {
				System.out.println(Error.EMAIL_BLANK.getDescription());
				continue;
			}

			if (isValidEmail(email) == false) {
				System.out.println(Error.EMAIL_NOT_VALID.getDescription());
				continue;
			}
			break;
		}
	}

	private void enterPassword() {
		while (true) {
			System.out.print("Enter password: ");
			password = scanner.nextLine();
			if (password.isEmpty()) {
				System.out.println(Error.PASSWORD_BLANK.getDescription());
				continue;
			}
			break;
		}
	}

	private boolean isValidEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}
