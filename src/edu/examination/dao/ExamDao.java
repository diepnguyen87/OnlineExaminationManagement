package edu.examination.dao;

import java.util.List;
import edu.examination.entity.ExamEntity;

public interface ExamDao {

	public List<ExamEntity> getAllExams();
	public ExamEntity getExam(int examID);
	public String getExamID(String examTitle);
	public int addExam(ExamEntity newExam);
	public int updateTotalQuestion(String examID, int totalQuestion);
}
