package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.ConsoleColors;
import edu.examination.config.Error;

public class MainClass {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void displayHomePage(){
		System.out.println(ConsoleColors.BLUE_BOLD + "WELCOME ONLINE EXAMINATION MANAGEMENT SYSTEM" + ConsoleColors.RESET);
		System.out.println("1. Admin");
		System.out.println("2. Instituation");
		System.out.println("3. User");
		
		outerloop:
		while(true){
			System.out.print("Select role (enter 1 or 2 or 3): ");
			String role = scanner.nextLine();
			switch (role) {
			case "1":
				System.out.println("==============ADMIN START==============");
				LoginPage adminLogin = new LoginPage(role);
				adminLogin.displayAdminLoginPage();
				break outerloop;
			case "2":
				System.out.println("==============INSTITUATION START==============");
				LoginPage instiLogin = new LoginPage(role);
				instiLogin.displayLoginPage();
				break outerloop;
			case "3":
				System.out.println("==============USER START==============");
				LoginPage userLogin = new LoginPage(role);
				userLogin.displayLoginPage();
				break outerloop;
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	
	public static void main(String[] args) {
		displayHomePage();
	}
}
