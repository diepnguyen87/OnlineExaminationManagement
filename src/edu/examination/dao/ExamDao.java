package edu.examination.dao;

import java.util.List;
import edu.examination.entity.ExamEntity;

public interface ExamDao {

	public List<ExamEntity> getAllExams();
	public List<ExamEntity> getSubmittedExams();
	public List<ExamEntity> getSubmittedExams_Admin(String examAuthorID);
	public List<ExamEntity> getSubmittedExams_Institute(String examAuthorID);
	public List<ExamEntity> getDraftExams_Admin(String examAuthorID);
	public List<ExamEntity> getDraftExams_Institute(String examAuthorID);
	public ExamEntity getExam(String examTitle);
	public String getExamID(String examTitle);
	public int addExam(ExamEntity newExam);
	public int updateTotalQuestion(String examID, int totalQuestion);
	public int updateExamTitle(String examTitle, String examID);
	public int updateExamDuration(int examDuration, String examID);
}
