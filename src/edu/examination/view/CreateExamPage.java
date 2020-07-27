package edu.examination.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.examination.config.Error;
import edu.examination.config.Message;
import edu.examination.controller.CreateExamController;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionEntity;

public class CreateExamPage extends CreateExamController {

	private Scanner scanner = new Scanner(System.in);
	private String examID, examTitle, examAuthor, questionID, questionText, optionText;
	private int examDuration, totalQuestion, questionMark;
	private List<OptionEntity> optionList;
	private ExamEntity newExam;
	private List<QuestionEntity> questionList;
	
	public CreateExamPage() {
	}

	public CreateExamPage(LoginPage loginPage) {
		super(loginPage);
		role = loginPage.role;
		newExam = new ExamEntity();
		if(role.equals("Admin")){
			newExam.setExamAdminAuthor(loginPage.getEmail());
		}else if(role.equals("Instituation")){
			newExam.setExamInstituationAuthor(loginPage.getEmail());
		}
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

		/*enterExamTitle();
		enterExamDuration();*/
		newExam.setExamTitle(enterExamTitle());
		newExam.setExamDuration(examDuration);
		//createExam(examTitle, examDuration, totalQuestion, examAuthor);
		
		//examID = getExamID(examTitle);
		
		outerLoop:
		while (true) {
			QuestionEntity newQuestion = new QuestionEntity();
			
			newQuestion.setQuestionText(enterQuestionText(totalQuestion));
			enterOptionText();
			newQuestion.setQuestionMark(enterQuestionMark());
			/*enterQuestionText(totalQuestion);
			enterOptionText();
			enterQuestionMark();*/
			//createQuestion(examID, questionText, questionMark);
			//questionID = getQuestionID(examID, questionText);

			for (int i = 0; i < optionList.size(); ++i) {
				//createOption(questionID, (i + 1), optionList.get(i));
			}
			++totalQuestion;
			System.out.println("============================");
			while(true){
				System.out.print("Add more question (enter Y/N)? ");
				option = scanner.nextLine().toUpperCase();
				switch (option) {
				case "Y":
					continue outerLoop;
				case "N":
					exam.updateTotalQuestion(examID, totalQuestion);
					break outerLoop;
				default:
					System.out.println(Error.INCORRECT_OPTION.getDescription());
					continue;
				}
			}
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
			if (isExamTitleDuplicated(examTitle)==true){
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
			System.out.printf("Enter question %s: ", (nthQuestion+1));
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
		while (true) {
			try {
				System.out.print("Enter question mark: ");
				questionMark = Integer.parseInt(scanner.nextLine());
				if (questionMark <= 0 || questionMark > 100) {
					System.out.println(Error.INVALID_QUESTION_MARK.getDescription());
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println(Error.NOT_A_NUMBER.getDescription());
				continue;
			}
			break;
		}
		return questionMark;
	}

	private List<OptionEntity> enterOptionText() {
		String option = "";
		String optionText = "";
		optionList = new ArrayList<OptionEntity>();
		int i=0;
		
		outerLoop: while (true) {
			//System.out.printf("Enter option %s: ", optionList.size() + 1);
			System.out.printf("Enter option %s: ", ++i);
			
			optionText = scanner.nextLine();
			if (optionText.isEmpty()) {
				System.out.println(Error.OPTION_TEXT_BLANK.getDescription());
				continue;
			}
			//optionList.add(optionText);
			optionList.add(new OptionEntity(i, optionText));
			innerLoop:
			while(true){
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
