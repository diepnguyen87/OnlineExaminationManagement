package edu.examination.view;

import java.util.List;
import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.config.Message;
import edu.examination.controller.EditExamController;
import edu.examination.entity.ExamEntity;

public class EditExamPage extends EditExamController {

	private Scanner scanner = new Scanner(System.in);
	private List<ExamEntity> draftExamsList;

	public EditExamPage() {

	}

	public EditExamPage(LoginPage loginPage) {
		super(loginPage);
	}

	public void displayEditExamPage() {
		String option = "";
		System.out.println("==============EDIT EXAM PAGE==============");
		System.out.println("1. Select exam to edit");
		System.out.println("2. Cancel");
		System.out.print("Select option (enter 1 or 2): ");
		option = scanner.nextLine();
		switch (option) {
		case "1":
			displayDraftExamsCreatedByYou();
			if(draftExamsList.size() == 0){
				break;
			}
			ExamEntity exam = selectDraftExamToEdit();
			selectItemToEdit(exam);
			break;
		case "2":
			HomePage homePage = new HomePage(loginPage);
			homePage.displayHomePage();
			break;
		default:
			System.out.println(Error.INCORRECT_OPTION.getDescription());
		}
	}

	private void displayDraftExamsCreatedByYou() {
		draftExamsList = displayDraftExams(loginPage.getEmail());
		if(draftExamsList.size() == 0){
			System.out.println(Message.NO_DRAFT_EXAM.getDescription());
			return;
		}
		System.out.printf("==============YOU HAVE %d DRAFT EXAMS AS BELOW==============%n", draftExamsList.size());
		System.out.println("Exam Title - Total Questions\n");

		for (int i = 0; i < draftExamsList.size(); ++i) {
			ExamEntity currentExam = draftExamsList.get(i);
			System.out.printf("%d. %s - %d questions%n", (i + 1), currentExam.getExamTitle(),
					currentExam.getTotalQuestion());
		}
	}

	private ExamEntity selectDraftExamToEdit() {
		ExamEntity exam = null;
		int option = 0;
		
		while (true) {
			try{
				System.out.print("Select exam title by entering option (1 or 2...): ");
				option = Integer.parseInt(scanner.nextLine());
					if (option <= draftExamsList.size()) {
						exam = getDraftExamByTitle(draftExamsList.get(option - 1).getExamTitle());
						break;
					} else {
						System.out.println(Error.INCORRECT_OPTION.getDescription());
					}
			}catch(NumberFormatException e){
				System.out.println(Error.NOT_A_NUMBER.getDescription());
			}
		}
		return exam;
	}
	
	private void selectItemToEdit(ExamEntity exam){
		String option = "";
		System.out.printf("==============EDITTING %s==============%n", exam.getExamTitle());
		
		outerLoop:
		while(true){
			System.out.println("1. Edit exam infos (title, duration)");
			System.out.println("2. Edit question infos (question text, question mark)");
			System.out.println("3. Edit exam infos (option text)");
			System.out.println("4. Cancel");
			
			System.out.print("Select above options to edit (1 or 2 or 3): ");
			option = scanner.nextLine();
			switch (option) {
			case "1":
				displayEditExamInfosScreen(exam);
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				HomePage homePage = new HomePage(loginPage);
				homePage.displayHomePage();
				break outerLoop;
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}
	
	private void displayEditExamInfosScreen(ExamEntity exam){
		String option = "";
		System.out.println("==============EDIT EXAM INFOS==============");
		
		outerLoop:
		while(true){
			System.out.println("1. Edit exam title");
			System.out.println("2. Edit exam duration");
			System.out.println("3. Cancel");
			System.out.print("Select above options to edit (1 or 2): ");
			option = scanner.nextLine();
			switch (option) {
			case "1":
				String newExamTitle = enterNewExamTitle();
				if(editExamTitle(newExamTitle, exam.getExamID())==true){
					System.out.println(Message.UPDATE_SUCCESSFUL.getDescription());
				}else{
					System.out.println(Message.UPDATE_FAIL.getDescription());
					//need insert more code to handle update fail
				}
				break outerLoop;
			case "2":
				int newExamDuration = enterNewExamDuration();
				if(editExamDuration(newExamDuration, exam.getExamID())==true){
					System.out.println(Message.UPDATE_SUCCESSFUL.getDescription());
				}else{
					System.out.println(Message.UPDATE_FAIL.getDescription());
					//need insert more code to handle update fail
				}
				break outerLoop;
			case "3":
				HomePage homePage = new HomePage(loginPage);
				homePage.displayHomePage();
				break outerLoop;
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}
	
	private String enterNewExamTitle(){
		String newExamTitle = "";
		while(true){
			System.out.print("Enter new exam title: ");
			newExamTitle = scanner.nextLine();
			
			if(newExamTitle.isEmpty()){
				System.out.println(Error.EXAM_TITLE_BLANK.getDescription());
				continue;
			}
			break;
		}
		return newExamTitle;
	}
	
	private int enterNewExamDuration(){
		int newExamDuration = 0;
		while (true) {
			try {
				System.out.print("Enter exam duration (mins): ");
				newExamDuration = Integer.parseInt(scanner.nextLine());
				if (newExamDuration <= 0) {
					System.out.println(Error.INVALID_EXAM_DURATION.getDescription());
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println(Error.NOT_A_NUMBER.getDescription());
			}
		}
		return newExamDuration;
	}
	
}
