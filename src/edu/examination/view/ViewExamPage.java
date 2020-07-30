package edu.examination.view;

import java.util.List;
import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.controller.ViewExamController;
import edu.examination.entity.ExamEntity;

public class ViewExamPage extends ViewExamController{
	
	private Scanner scanner = new Scanner(System.in);
	
	public ViewExamPage(){
		
	}
	
	public ViewExamPage(LoginPage loginPage){
		super(loginPage);
	}
	
	public void displayViewExamPage(){
		String option = "";
		System.out.println("==============VIEW EXAM PAGE==============");
		System.out.println("1. Select exam to view");
		System.out.println("2. Cancel");
		System.out.print("Select option (enter 1 or 2): ");
		option = scanner.nextLine();
		switch (option) {
		case "1":
			displaySubmittedExamsCreatedByYou();
			break;
		case "2":
			break;
		default:
			System.out.println(Error.INCORRECT_OPTION.getDescription());
		}
	}
	
	private void displaySubmittedExamsCreatedByYou(){	
		List<ExamEntity> submittedExamsList = displaySubmittedExams(loginPage.getEmail());
		System.out.printf("==============YOU SUBMITTED %d EXAMS AS BELOW==============%n", submittedExamsList.size());
		
		System.out.println("Exam Title - Total Questions\n");
		/*for(ExamEntity currentExam : submittedExamsList){
			System.out.println(currentExam.getExamTitle() + " - " + currentExam.getTotalQuestion() + " questions");
		}*/
		for(int i = 0; i < submittedExamsList.size(); ++i){
			ExamEntity currentExam = submittedExamsList.get(i);
			System.out.printf("%d. %s - %d questions%n", (i+1), currentExam.getExamTitle(), currentExam.getTotalQuestion());
		}
	}
}
