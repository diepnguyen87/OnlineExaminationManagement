package edu.examination.dao;

import java.util.List;
import edu.examination.entity.QuestionEntity;

public interface QuestionDao {

	public List<QuestionEntity> getAllQuestions();
	public QuestionEntity getQuestion(int questionID);
	public int addQuestion(QuestionEntity newQuestion);
	public String getQuestionID(String examID, String questionText);
}
