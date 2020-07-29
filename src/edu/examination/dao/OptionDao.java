package edu.examination.dao;

import java.util.List;

import edu.examination.entity.OptionEntity;

public interface OptionDao {

	public List<OptionEntity> getAllOptions();
	public List<OptionEntity> getOptionByQuestionID(String questionID);
	public int addOption(OptionEntity newOption);
	public OptionEntity getOption(String questionID, int optionNumber);
}
