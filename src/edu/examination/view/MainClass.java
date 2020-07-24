package edu.examination.view;

import java.util.Scanner;

import edu.examination.config.ConsoleColors;

public class MainClass {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void displayHomePage(){
		System.out.println(ConsoleColors.RED + "RED COLORED" +
				ConsoleColors.RESET + " NORMAL");
		System.out.println("WELCOME ONLINE EXAMINATION MANAGEMENT SYSTEM");
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
				System.out.println("WARNING: INCORRECT OPTION. PLEASE ENTER AGAIN!");
			}
		}
	}

	
	public static void main(String[] args) {
		displayHomePage();
	}
}
