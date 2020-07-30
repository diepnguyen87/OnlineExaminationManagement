package edu.examination.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.examination.config.ConsoleColors;
import edu.examination.config.Error;
import edu.examination.config.Message;
import edu.examination.controller.CreateExamController;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionAnswerEntity;
import edu.examination.entity.QuestionEntity;

public class CreateExamPage extends CreateExamController {

	private Scanner scanner = new Scanner(System.in);
	private String examID, examAuthor, questionID;
	private ExamEntity newExam;
	private List<QuestionEntity> questionList = new ArrayList<QuestionEntity>();
	private List<List<OptionEntity>> allOptions = new ArrayList<>();
	private List<Integer> correctionOptions = new ArrayList<>();
	private int sum;
	
	public CreateExamPage() {
	}

	public CreateExamPage(LoginPage loginPage) {
		super(loginPage);
		newExam = new ExamEntity();
		examAuthor = loginPage.getEmail();
	}

	public void displayCreateExamPage() {
		System.out.println("==============CREATE EXAM PAGE==============");
		System.out.println("1. Create exam");
		System.out.println("2. Exit");
		outerloop: while (true) {
			System.out.print("Select option (enter 1 or 2): ");
			String option = scanner.nextLine();
			switch (option) {
			case "1":
				createExam();
				break outerloop;
			case "2":
				System.out.println(Message.EXIT_APP.getDescription());
				System.exit(0);
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	private void createExam() {
		String option = "";
		newExam.setExamTitle(enterExamTitle());
		newExam.setExamDuration(enterExamDuration());

		outerLoop: while (sum < 100) {
			QuestionEntity newQuestion = new QuestionEntity();

			newQuestion.setQuestionText(enterQuestionText(questionList.size()));
			List<OptionEntity> optionList = enterOptionText();
			
			questionList.add(newQuestion);
			allOptions.add(optionList);
			correctionOptions.add(enterCorrectAnswer(optionList));
			newQuestion.setQuestionMark(enterQuestionMark());
			
			System.out.println("============================");
			while (sum < 100) {
				System.out.print("Add more question (enter Y/N)? ");
				option = scanner.nextLine().toUpperCase();
				switch (option) {
				case "Y":
					continue outerLoop;
				case "N":
					break outerLoop;
				default:
					System.out.println(Error.INCORRECT_OPTION.getDescription());
					continue;
				}
			}
		}
		displaySubmit();
	}

	private void displaySubmit() {
		newExam.setTotalQuestion(questionList.size());
		if (sum < 100) {
			menu_notEnoughMark();
		} else {
			menu_EnoughMark();
		}
	}

	private void menu_EnoughMark() {
		String option = "";
		System.out.println("Do you want to submit or save as draft: ");
		System.out.println("1. Submit");
		System.out.println("2. Save as draft");
		System.out.println("3. Cancel");

		outerLoop: while (true) {
			System.out.print("Select option (enter 1 or 2): ");
			option = scanner.nextLine();
			switch (option) {
			case "1":
				save();
				System.out.println(Message.EXAM_SUBMIT_SUCCESSFUL.getDescription());
				break outerLoop;
			case "2":
				newExam.setIsDraft("Y");
				save();
				System.out.println(Message.EXAM_SAVE_AS_DRAFT_SUCCESSFUL.getDescription());
				break outerLoop;
			case "3":
				HomePage homePage = new HomePage(loginPage);
				homePage.displayHomePage();
				//break;
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	private void menu_notEnoughMark() {
		String option = "";
		System.out.println(Message.TOTAL_MARK_NOT_ENOUGH.getDescription());
		System.out.println("1. Save as draft");
		System.out.println("2. Cancel");
		outerLoop: while (true) {
			System.out.print("Select option (enter 1 or 2): ");
			option = scanner.nextLine();
			switch (option) {
			case "1":
				newExam.setIsDraft("Y");
				save();
				System.out.println(Message.EXAM_SAVE_AS_DRAFT_SUCCESSFUL.getDescription());
				break outerLoop;
			case "2":
				HomePage homePage = new HomePage(loginPage);
				homePage.displayHomePage();
				//break;
			default:
				System.out.println(Error.INCORRECT_OPTION.getDescription());
			}
		}
	}

	private void save() {
		List<OptionEntity> optionList;
		
		createExam(newExam, examAuthor);
		examID = getExamID(newExam.getExamTitle());
		for (int i = 0; i < questionList.size(); ++i) {
			QuestionEntity currentQuestion = questionList.get(i);
			createQuestion(examID, currentQuestion.getQuestionText(), currentQuestion.getQuestionMark());
			questionID = getQuestionID(examID, currentQuestion.getQuestionText());
			optionList = allOptions.get(i);
			for (int j = 0; j < optionList.size(); ++j) {
				OptionEntity currentOption = optionList.get(j);
				createOption(questionID, currentOption.getOptionNumber(), currentOption.getOptionText());
			}
			//String optionID = option.getOption(questionID, correctionOptions.get(i).getOptionNumber()).getOptionID();
			String optionID = option.getOption(questionID, correctionOptions.get(i)).getOptionID();
			createQuestionAnswers(new QuestionAnswerEntity(questionID, optionID));
		}
	}

	private String enterExamTitle() {
		String examTitle = "";
		while (true) {
			System.out.print("Enter exam title: ");
			examTitle = scanner.nextLine();
			if (examTitle.isEmpty()) {
				System.out.println(Error.EXAM_TITLE_BLANK.getDescription());
				continue;
			}
			if (isExamTitleDuplicated(examTitle) == true) {
				System.out.println(Error.EXAM_TITLE_DUPLICATED.getDescription());
				continue;
			}
			break;
		}
		return examTitle;
	}

	private int enterExamDuration() {
		int examDuration = 0;
		while (true) {
			try {
				System.out.print("Enter exam duration (mins): ");
				examDuration = Integer.parseInt(scanner.nextLine());
				if (examDuration <= 0) {
					System.out.println(Error.INVALID_EXAM_DURATION.getDescription());
					continue;
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println(Error.NOT_A_NUMBER.getDescription());
			}
		}
		return examDuration;
	}

	private String enterQuestionText(int nthQuestion) {
		String questionText = "";
		while (true) {
			System.out.printf("Enter question %s: ", (nthQuestion + 1));
			questionText = scanner.nextLine();
			if (questionText.isEmpty()) {
				System.out.println(Error.QUESTION_TEXT_BLANK.getDescription());
				continue;
			}
			break;
		}
		return questionText;
	}

	private int enterQuestionMark() {
		int questionMark = 0;
		int totalMark = 0;
		String option = "";

		outerLoop: while (true) {
			try {
				System.out.print("Enter question mark: ");
				questionMark = Integer.parseInt(scanner.nextLine());
				if (questionMark <= 0 || questionMark > 100) {
					System.out.println(Error.INVALID_QUESTION_MARK.getDescription());
					continue;
				}
				totalMark = sum + questionMark;
				if (totalMark > 100) {
					System.out.printf(
							ConsoleColors.RED + "Total mark of 1 exam must be 100 and your current total mark is %d%n"
									+ ConsoleColors.RESET,
							totalMark);

					while (true) {
						System.out.print("Do you want to re-enter question mark (Y/N)? ");
						option = scanner.nextLine().toUpperCase();
						switch (option) {
						case "Y":
							continue outerLoop;
						case "N":
							System.out
									.println("The mark of last question will be re-calculated to match with condition");
							System.out.println("The mark you entered for last question is: " + questionMark);
							questionMark = 100 - sum;
							System.out.println("The app re-calculated into: " + questionMark);
							break outerLoop;
						default:
							System.out.println(Error.INCORRECT_OPTION.getDescription());
						}
					}
				}
			} catch (NumberFormatException e) {
				System.out.println(Error.NOT_A_NUMBER.getDescription());
				continue;
			}
			break;
		}
		sum += questionMark;
		return questionMark;
	}

	
	private int enterCorrectAnswer(List<OptionEntity> optionList){
		int correctionOption = 0;
		int option = 0;
		while(true){
			try{
				System.out.print("Enter correct answer by option number: ");
				option = Integer.parseInt(scanner.nextLine());
				if(option <= 0 || option > optionList.size()){
					System.out.println(Error.INCORRECT_OPTION.getDescription());
					continue;
				}
				/*correctionOption = optionList.get(option-1);*/
				correctionOption = option;
				break;
			}catch(NumberFormatException e){
				System.out.println(Error.NOT_A_NUMBER.getDescription());
			}
		}
		return correctionOption;
	}
	
	private List<OptionEntity> enterOptionText() {
		String option = "";
		String optionText = "";
		List<OptionEntity> optionList = new ArrayList<OptionEntity>();
		int i = 0;

		outerLoop: while (true) {
			System.out.printf("Enter option %s: ", ++i);

			optionText = scanner.nextLine();
			if (optionText.isEmpty()) {
				System.out.println(Error.OPTION_TEXT_BLANK.getDescription());
				continue;
			}
			optionList.add(new OptionEntity(i, optionText));
			
			
			innerLoop: while (true) {
				System.out.print("Add more option (enter Y/N)? ");
				option = scanner.nextLine().toUpperCase();
				switch (option) {
				case "Y":
					continue outerLoop;
				case "N":
					break outerLoop;
				default:
					System.out.println(Error.INCORRECT_OPTION.getDescription());
					continue innerLoop;
				}
			}
		}
		return optionList;
	}

}
