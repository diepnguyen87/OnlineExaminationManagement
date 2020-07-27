package edu.examination.dao;

import java.util.List;

import edu.examination.entity.OptionEntity;

public interface OptionDao {

	public List<OptionEntity> getAllOptions();
	public OptionEntity getOption(int optionID);
	public int addOption(OptionEntity newOption);
}
