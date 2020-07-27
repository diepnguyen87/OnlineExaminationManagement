package edu.examination.dao;

import java.util.List;
import edu.examination.entity.QuestionAnswerEntity;

public interface QuestionAnswerDao {

	public List<QuestionAnswerEntity> getAllQuestionAnswers();
	public QuestionAnswerEntity getQuestionAnswer(int questionID);
	public int addQuestionAnswer(QuestionAnswerEntity newQuestionAnswer);
}
