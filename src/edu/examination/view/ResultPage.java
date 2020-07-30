package edu.examination.view;

import java.util.List;

import edu.examination.controller.ResultController;
import edu.examination.entity.ExamEntity;
import edu.examination.entity.OptionEntity;
import edu.examination.entity.QuestionAnswerEntity;
import edu.examination.entity.QuestionEntity;
import edu.examination.entity.UserAnswerEntity;

public class ResultPage extends ResultController {

	private List<UserAnswerEntity> userAnswerList;
	private List<QuestionAnswerEntity> questionAnswerList;
	private List<QuestionEntity> questionList;
	private List<OptionEntity> optionList;
	
	public ResultPage(){
		
	}
	
	public ResultPage(LoginPage loginPage, ExamEntity exam){
		super(loginPage, exam);
	}
	
	public void displayResultPage(){
		System.out.println("==============RESULT PAGE==============");
		userAnswerList = getUserAnswersByExamID();
		questionAnswerList = getQuestionAnswers();
		for(int i =0; i < questionList.size(); ++i){
			QuestionEntity currentQuestion = questionList.get(i);
			optionList = getOptionListByQuestionID(currentQuestion.getQuestionID());
			QuestionAnswerEntity currentQuestionAnswer = questionAnswerList.get(i);
			UserAnswerEntity currentUserAnswer = userAnswerList.get(i);
			System.out.printf("Question %d: %s (%d)%n", (i+1), currentQuestion.getQuestionText(), currentQuestion.getQuestionMark());
			
			System.out.printf("Correct answer: %d: %n", getOptionTextByOptionID(optionList, currentQuestionAnswer.getOptionID()));
			System.out.printf("Correct answer: %d: %n", getOptionTextByOptionID(optionList, currentUserAnswer.getUserOptionID()));
			
		}
	}
}
