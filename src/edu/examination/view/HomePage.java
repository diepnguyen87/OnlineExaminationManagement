package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.config.Message;

public class HomePage{
	
	private Scanner scanner = new Scanner(System.in);
	private LoginPage loginPage;
	
	public HomePage(){
		
	}
	
	public HomePage(LoginPage loginPage){
		this.loginPage = loginPage;
	}
	
	public void displayHomePage(){
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
			case "4":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
		
	}
}
