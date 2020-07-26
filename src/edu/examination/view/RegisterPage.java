package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.config.Message;
import edu.examination.controller.RegisterController;

public class RegisterPage extends RegisterController {

	private Scanner scanner = new Scanner(System.in);
	private LoginPage loginPage;
	private String emailAddress;
	private String password;

	public RegisterPage() {
	}

	public RegisterPage(LoginPage loginPage) {
		super(loginPage.role);
		this.loginPage = loginPage;
	}

	public void register() {
		System.out.println("==============REGISTER PAGE==============");
		enterEmail();
		enterPassword();
		if (register(emailAddress, password) == true) {
			System.out.println(Message.REGISTER_SUCCESSFUL.getDescription());
			loginPage.displayLoginPage();
		}
		// thieu case register=false
	}

	private void enterEmail() {
		while (true) {
			System.out.print("Enter email: ");
			emailAddress = scanner.nextLine();
			if (emailAddress.isEmpty()) {
				System.out.println(Error.EMAIL_BLANK.getDescription());
				continue;
			}

			if (isValidEmail(emailAddress) == false) {
				System.out.println(Error.EMAIL_NOT_VALID.getDescription());
				continue;
			}

			if (isEmailDuplicated(emailAddress) == true) {
				System.out.println(Error.EMAIL_EXISTED.getDescription());
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

			if (isValidPassword(password) == false) {
				System.out.println(Error.PASSWORD_NOT_VALID.getDescription());
				continue;
			}
			break;
		}
	}

}
