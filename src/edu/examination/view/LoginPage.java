package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.ConsoleColors;
import edu.examination.config.Error;
import edu.examination.config.Message;
import edu.examination.controller.LoginController;

public class LoginPage extends LoginController {

	private Scanner scanner = new Scanner(System.in);
	private String email;
	private String password;

	public LoginPage() {

	}

	public LoginPage(String roleOption) {
		super(roleOption);
	}

	public String getEmail(){
		return this.email;
	}
	
	public void displayAdminLoginPage() {
		System.out.println("==============LOGIN PAGE==============");
		System.out.println("1. Login");
		System.out.println("2. Exit");
		outerloop: while (true) {
			System.out.print("Select option (enter 1 or 2): ");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				login();
				break outerloop;
			case "2":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
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
				RegisterPage register = new RegisterPage(this);
				register.register();
				break outerloop;
			case "3":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());

			}
		}
	}

	private void login() {
		outerloop: while (true) {
			enterEmail();
			enterPassword();
			if (login(email, password) == true) {
				System.out.println(Message.LOGIN_SUCCESSFUL.getDescription());
				System.out.printf(ConsoleColors.BLUE_BOLD + "WELCOME %s TO ONLINE EXAMINATION MANAGEMENT SYSTEM%n"
						+ ConsoleColors.RESET, email.toUpperCase());
				HomePage homePage = new HomePage(this);
				homePage.displayHomePage();
				return;
			} else {
				System.out.println(Error.INCORRECT_AUTHENICATION.getDescription());
				System.out.print("TRY AGAIN (enter Y/N)? ");
				String option = scanner.nextLine().toUpperCase();

				switch (option) {
				case "N":
					break outerloop;
				default:
					continue;
				}
			}
		}
		if (role.equals("Admin")) {
			displayAdminLoginPage();
		} else {
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
