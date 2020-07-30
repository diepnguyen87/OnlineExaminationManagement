package edu.examination.dao;

import java.util.List;

import edu.examination.entity.QuestionAnswerEntity;

public interface QuestionAnswerDao {

	public int addQuestionAnswer(QuestionAnswerEntity newQuestionAnswer);
	public List<QuestionAnswerEntity> getQuestionAnswerByExamID(String examID);
}
