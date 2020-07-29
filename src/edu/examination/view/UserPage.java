package edu.examination.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.examination.config.ConsoleColors;
import edu.examination.config.Error;
import edu.examination.controller.UserController;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionEntity;
import edu.examination.entity.UserAnswerEntity;

public class UserPage extends UserController{

	private Scanner scanner = new Scanner(System.in);
	private ExamEntity selectedExam;
	private List<OptionEntity> userAnswers = new ArrayList<OptionEntity>();
	List<QuestionEntity> questionList;
	public boolean isSubmitted = false;
	private Thread timeoutThread;
	
	public UserPage() {

	}

	public UserPage(LoginPage loginPage) {
		super(loginPage);
	}

	public void displayUserPage() {
		int option = 0;
		List<ExamEntity> allSubmittedExams = getSubmittedExams();
		displayMenu_User(allSubmittedExams);

		while (true) {
			try {
				System.out.print("Select exam by entering option (1 or 2...): ");
				option = Integer.parseInt(scanner.nextLine());
				if (option <= allSubmittedExams.size()) {
					selectedExam = getExamByTitle(allSubmittedExams.get(option - 1).getExamTitle());
					displayStartExam_Menu();
					break;
				} else if (option == allSubmittedExams.size() + 1) {
					HomePage homePage = new HomePage();
					homePage.displayHomePage_User();
				}
			} catch (NumberFormatException e) {
				System.out.println(Error.NOT_A_NUMBER.getDescription());
			}
		}
	}

	private void displayMenu_User(List<ExamEntity> allSubmittedExams) {
		System.out.printf("==============CURRENTLY HAVE %d EXAMS AS BELOW==============%n", allSubmittedExams.size());
		System.out.println("Exam Title - Total Questions\n");

		for (int i = 0; i < allSubmittedExams.size(); ++i) {
			ExamEntity currentExam = allSubmittedExams.get(i);
			System.out.printf("%d. %s - %d questions%n", (i + 1), currentExam.getExamTitle(), currentExam.getTotalQuestion());
		}
		System.out.printf("%d. Cancel%n", allSubmittedExams.size() + 1);
	}

	private void displayStartExam_Menu(){
		String option = "";
		System.out.println("1. Start exam");
		System.out.println("2. Cancel");
		
		outerLoop:
		while(true){
			System.out.print("Select option (enter 1 or 2): ");
			option = scanner.nextLine();
			switch (option) {
			case "1":
				  timeoutThread = new Thread() { 
					  public void run() { 
						  try {
						  Thread.sleep(selectedExam.getExamDuration()*60*1000);
						  submit();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} } };
				timeoutThread.start();
				displayQuestionsByExam(selectedExam);
				displayMenu_Submit();
				break outerLoop;
			case "2":
				HomePage homePage = new HomePage(loginPage);
				homePage.displayHomePage();
				break outerLoop;
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	private void displayQuestionsByExam(ExamEntity exam) throws InterruptedException {
		int option = 0;
		System.out.printf("==============STARTING %s==============%n", selectedExam.getExamTitle());
		questionList = getAllQuestionsByExamID(selectedExam.getExamID());
		for (int i = 0; i < questionList.size(); ++i) {
			QuestionEntity currentQuestion = questionList.get(i);
			System.out.printf(ConsoleColors.CYAN_BACKGROUND + "Question %d. %s (%d marks)%n" + ConsoleColors.RESET, (i + 1), currentQuestion.getQuestionText(), currentQuestion.getQuestionMark());
			List<OptionEntity> optionList = getAllOptionsByQuestionID(currentQuestion.getQuestionID());
			for (int j = 0; j < optionList.size(); ++j) {
				OptionEntity currentOption = optionList.get(j);
				System.out.printf("%d. %s%n", currentOption.getOptionNumber(), currentOption.getOptionText());
			}
			System.out.printf("%d. Skip%n", optionList.size() + 1);
			while (true) {
				try {
					System.out.print("Your answer (enter 1/2/3...): ");
					while(isSubmitted = false) {
						option = Integer.parseInt(scanner.nextLine());
						Thread.sleep(100);
					}
				
					if (option > optionList.size() + 1) {
						System.out.println(Error.INCORRECT_OPTION.getDescription());
						continue;
					}
					userAnswers.add(optionD.getOption(currentQuestion.getQuestionID(), option));
					break;
				} catch (NumberFormatException e) {
					System.out.println(Error.NOT_A_NUMBER.getDescription());
				}
			}
		}fgf
	}

	private synchronized void displayMenu_Submit() {
		System.out.println("============================");
		int option = 0;

		outLoop: while (true) {
			try {
				System.out.println("1. Submit");
				System.out.println("2. Cancel");
				System.out.print("Do you want to submit or cancel: ");
				option = Integer.parseInt(scanner.nextLine());
				switch (option) {
				case 1:
					submit();
					
					break outLoop;
				case 2:
					break;
				default:
					System.out.println(Error.INCORRECT_OPTION.getDescription());
				}
			} catch (NumberFormatException e) {
				System.out.println(Error.NOT_A_NUMBER.getDescription());
			}
		}
	}

	private synchronized boolean submit() {
		String optionID = null;
		if(isSubmitted == false) {
			String userID = getUserID(loginPage.getEmail());
			String examID = selectedExam.getExamID();
			
			for (int i = 0; i < questionList.size(); ++i) {
				QuestionEntity currentQuestion = questionList.get(i);
				if(userAnswers.size() > 0 && userAnswers.get(i) != null) {
					optionID = getOptionID(currentQuestion.getQuestionID(), userAnswers.get(i).getOptionNumber());
				}
				
				insertUserAnswer(new UserAnswerEntity(userID, examID, currentQuestion.getQuestionID(), optionID));
			}
			isSubmitted = true;
			return true;
		}
		return false;
	}
}
