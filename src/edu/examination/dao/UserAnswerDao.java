package edu.examination.dao;

import java.util.List;

import edu.examination.entity.UserAnswerEntity;

public interface UserAnswerDao {
	
	public int addUserAnswer(UserAnswerEntity userAnswer);
	public List<UserAnswerEntity> getUserAnswerByExamID(String userID, String examID);
}
