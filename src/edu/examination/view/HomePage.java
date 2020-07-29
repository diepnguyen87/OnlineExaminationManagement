package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.config.Message;

public class HomePage {

	private Scanner scanner = new Scanner(System.in);
	private LoginPage loginPage;

	public HomePage() {

	}

	public HomePage(LoginPage loginPage) {
		this.loginPage = loginPage;
	}

	public void displayHomePage() {
		String role = loginPage.role;
		if (role.equals("Admin")) {
			displayHomePage_Admin();
		} else if (role.equals("Instituation")) {
			displayHomePage_Instituation();
		} else if (role.equals("User")) {
			displayHomePage_User();
		}
	}

	public void displayHomePage_Admin() {
		System.out.println("==============HOME PAGE==============");
		System.out.println("1. Create exam");
		System.out.println("2. View exam");
		System.out.println("3. Edit draft exam");
		System.out.println("4. Maintain user");
		System.out.println("5. Maintain instituation");
		System.out.println("6. Exit");
		outerloop: while (true) {
			System.out.print("Select option (enter 1/2/3/4): ");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				CreateExamPage createExamPage = new CreateExamPage(loginPage);
				createExamPage.displayCreateExamPage();
				break outerloop;
			case "2":
				ViewExamPage vxp = new ViewExamPage(loginPage);
				vxp.displayViewExamPage();
				break outerloop;
			case "3":
				EditExamPage exp = new EditExamPage(loginPage);
				exp.displayEditExamPage();
				break outerloop;
			case "6":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	public void displayHomePage_Instituation() {
		System.out.println("==============HOME PAGE==============");
		System.out.println("1. Create exam");
		System.out.println("2. View exam");
		System.out.println("3. Edit draft exam");
		System.out.println("4. Exit");
		outerloop: while (true) {
			System.out.print("Select option (enter 1/2/3/4): ");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				CreateExamPage createExamPage = new CreateExamPage(loginPage);
				createExamPage.displayCreateExamPage();
				break outerloop;
			case "2":
				ViewExamPage vxp = new ViewExamPage(loginPage);
				vxp.displayViewExamPage();
				break outerloop;
			case "3":
				EditExamPage exp = new EditExamPage(loginPage);
				exp.displayEditExamPage();
				break outerloop;
			case "4":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	public void displayHomePage_User() {
		System.out.println("==============HOME PAGE==============");
		System.out.println("1. Display all exams");
		System.out.println("2. Exit");
		outerloop: while (true) {
			System.out.print("Select option (enter 1/2/3/4): ");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				UserPage userPage = new UserPage(loginPage);
				userPage.displayUserPage();
				break outerloop;
			case "2":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}
}
